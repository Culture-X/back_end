package TripAmi.backend.auth.authmember.infra;

import TripAmi.backend.app.member.domain.Ami;
import TripAmi.backend.app.member.domain.Traveler;
import TripAmi.backend.app.member.service.exception.MemberNotFound;
import TripAmi.backend.app.util.service.MailService;
import TripAmi.backend.auth.authmember.domain.AuthMember;
import TripAmi.backend.auth.authmember.domain.AuthMemberRepository;
import TripAmi.backend.auth.authmember.domain.Role;
import TripAmi.backend.auth.authmember.exception.PasswordMismatchException;
import TripAmi.backend.auth.authmember.service.AuthCodeService;
import TripAmi.backend.auth.authmember.service.AuthMemberService;
import TripAmi.backend.auth.authmember.service.dto.AuthMemberDto;
import TripAmi.backend.auth.authmember.service.dto.LoginDto;
import TripAmi.backend.auth.authmember.service.dto.PasswordPatternChecker;
import TripAmi.backend.auth.authmember.service.exception.DuplicatedEmailException;
import TripAmi.backend.auth.authmember.service.exception.DuplicatedNicknameException;
import TripAmi.backend.auth.authmember.service.exception.EmailNotFoundException;
import TripAmi.backend.auth.exception.BusinessLogicException;
import TripAmi.backend.auth.exception.ExceptionCode;
import TripAmi.backend.auth.jwt.service.RedisService;
import TripAmi.backend.auth.jwt.service.dto.TokenDto;
import TripAmi.backend.auth.security.JwtProvider;
import TripAmi.backend.auth.security.infra.CustomUserDetails;
import TripAmi.backend.config.AES128Config;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;

import static TripAmi.backend.auth.authmember.domain.MemberStatus.WITHDRAWAL;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthMemberServiceImpl implements AuthMemberService {

    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final AuthMemberRepository authMemberRepository;
    private final AuthCodeService authCodeService;
    private final PasswordPatternChecker passwordPatternChecker;
    private final MailService mailService;
    private final AES128Config aes128Config;
    private final RedisService redisService;

    @Override
    @Transactional
    public void authenticateEmail(String email, LocalDateTime inputTime) {
        validateUniqueEmail(email);
        // todo AuthCode 찾아서 save or update
        String code = authCodeService.getAuthCode(email, inputTime);
        mailService.sendEmail(email, code);
    }

    @Override
    public void validateUniqueEmail(String email) {
        if (authMemberRepository.existsAuthMemberByEmail(email))
            throw new DuplicatedEmailException();
    }

    @Override
    public void validateUniqueNickname(String nickname) {
        if (authMemberRepository.existsAuthMemberByNickname(nickname))
            throw new DuplicatedNicknameException();
    }

    @Override
    public void validatePasswordPattern(String password) {
        passwordPatternChecker.checkCharsCombination(password);
    }

    @Override
    @Transactional
    public void join(String email, String nickname, String password, Boolean agreedMarketing) {
        Traveler traveler = new Traveler();
        Ami ami = new Ami();
        authCodeService.verifyConfirmedEmail(email);
        AuthMember authMember = AuthMember.builder()
                                    .email(email)
                                    .passwordEncoder(passwordEncoder)
                                    .raw(password)
                                    .nickname(nickname)
                                    .agreedMarketing(agreedMarketing)
                                    .role(Role.ROLE_MEMBER)
                                    .traveler(traveler)
                                    .ami(ami)
                                    .build();
        authMemberRepository.save(authMember);
    }

    @Override
    public void updatePassword(Long authMemberId, String password) {
        passwordPatternChecker.checkCharsCombination(password);
        AuthMember authMember = authMemberRepository.findById(authMemberId).orElseThrow(MemberNotFound::new);
        authMember.updatePassword(password, passwordEncoder);
        // todo update 로직 추가
    }

    @Override
    @Transactional
    public void withdrawal(Long authMemberId, String reason) {
        AuthMember authMember = authMemberRepository.findById(authMemberId).orElseThrow(MemberNotFound::new);
        authMember.updateStatus(WITHDRAWAL);
        authMember.delete();
    }

    @Override
    @Transactional
    public void findAccount(String email, LocalDateTime inputTime) {
        AuthMember authMember = authMemberRepository.findByEmail(email).orElseThrow(EmailNotFoundException::new);
        String code = authCodeService.getAuthCode(email, inputTime);
        mailService.sendEmail(email, code);
    }

    @Override
    @Transactional
    public LoginDto login(String email, String password) {
        AuthMember authMember = authMemberRepository.findByEmail(email).orElseThrow(EmailNotFoundException::new);
        if (!passwordEncoder.matches(password, authMember.getPassword())) {
            throw new PasswordMismatchException("야 제대로 해");
        }
        return LoginDto.builder()
//                   .memberId(authMember.getId())
                   .email(authMember.getEmail())
//                   .nickname(authMember.getNickname())
//                   .imgUrl(authMember.getImgUrl())
//                   .imgUrl("imgUrl")
                   .password(authMember.getPassword())
                   .build();

    }

    @Override
    public void logout(String encryptedRefreshToken, String accessToken) {
        this.verifiedRefreshToken(encryptedRefreshToken);
        String refreshToken = aes128Config.decryptAes(encryptedRefreshToken);
        Claims claims = jwtProvider.parseClaims(refreshToken);
        String email = claims.getSubject();
        String redisRefreshToken = redisService.getValues(email);
        if (redisService.checkExistsValue(redisRefreshToken)) {
            redisService.deleteValues(email);

            // 로그아웃 시 Access Token Redis 저장 ( key = Access Token / value = "logout" )
            long accessTokenExpirationMillis = jwtProvider.getAccessTokenExpirationMillis();
            redisService.setValues(accessToken, "logout", Duration.ofMillis(accessTokenExpirationMillis));
        }
    }


    @Override
    @Transactional
    public AuthMemberDto selectRole(String email, Role role) {
        AuthMember authMember = authMemberRepository.findByEmail(email).orElseThrow(MemberNotFound::new);
        authMember.switchRole(role);
        return AuthMemberDto.builder()
                   .email(authMember.getEmail())
                   .nickname(authMember.getNickname())
                   .build();
    }

    @Override
    public AuthMember findAuthMember(String email) {
        return authMemberRepository.findByEmail(email).orElseThrow(MemberNotFound::new);
    }

    @Override
    public AuthMember findAuthMember(Long id) {
        return authMemberRepository.findById(id).orElseThrow(MemberNotFound::new);
    }

    @Override
    @Transactional
    public void updateNickname(Long authMemberId, String nickname) {
        AuthMember authMember = authMemberRepository.findById(authMemberId).orElseThrow(MemberNotFound::new);
        if (authMemberRepository.existsAuthMemberByNickname(nickname))
            throw new DuplicatedNicknameException();
        authMember.updateNickname(nickname);
    }

    private void verifiedRefreshToken(String encryptedRefreshToken) {
        if (encryptedRefreshToken == null) {
            throw new BusinessLogicException(ExceptionCode.HEADER_REFRESH_TOKEN_NOT_EXISTS);
        }
    }

    private AuthMember findAuthMemberByEmail(String email) {
        return authMemberRepository.findByEmail(email).orElseThrow(MemberNotFound::new);
    }

    @Override
    public String reissueAccessToken(String encryptedRefreshToken) {
        this.verifiedRefreshToken(encryptedRefreshToken);
        String refreshToken = aes128Config.decryptAes(encryptedRefreshToken);
        Claims claims = jwtProvider.parseClaims(refreshToken);
        String email = claims.getSubject();
        String redisRefreshToken = redisService.getValues(email);

        if (redisService.checkExistsValue(redisRefreshToken) && refreshToken.equals(redisRefreshToken)) {
            AuthMember findAuthMember = this.findAuthMemberByEmail(email);
            CustomUserDetails userDetails = CustomUserDetails.of(findAuthMember);
            TokenDto tokenDto = jwtProvider.generateTokenDto(userDetails);
            String newAccessToken = tokenDto.accessToken();
            long refreshTokenExpirationMillis = jwtProvider.getRefreshTokenExpirationMillis();

            return newAccessToken;
        } else throw new BusinessLogicException(ExceptionCode.TOKEN_IS_NOT_SAME);
    }

}
