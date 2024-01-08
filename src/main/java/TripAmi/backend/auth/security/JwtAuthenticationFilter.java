package TripAmi.backend.auth.security;

import TripAmi.backend.app.util.Responder;
import TripAmi.backend.auth.authmember.domain.AuthMember;
import TripAmi.backend.auth.authmember.domain.Role;
import TripAmi.backend.auth.authmember.service.AuthMemberService;
import TripAmi.backend.auth.authmember.service.dto.LoginDto;
import TripAmi.backend.auth.jwt.service.RedisService;
import TripAmi.backend.auth.jwt.service.dto.TokenDto;
import TripAmi.backend.auth.security.infra.CustomUserDetails;
import TripAmi.backend.config.AES128Config;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.util.*;

// jwt가 유효성을 검증하는 필터
@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final AES128Config aes128Config;
    private final AuthMemberService authMemberService;
    private final RedisService redisService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtProvider jwtProvider, AES128Config aes128Config, AuthMemberService authMemberService, RedisService redisService) {
        super(authenticationManager);
        this.jwtProvider = jwtProvider;
        this.aes128Config = aes128Config;
        this.authMemberService = authMemberService;
        this.redisService = redisService;
    }

    //@SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        log.info("JwtAuthenticationFilter.attemptAuthentication");
        // ServletInputStream을 LoginDto 객체로 역직렬화
        ObjectMapper objectMapper = new ObjectMapper();
        LoginDto loginDto = null;
        try {
            loginDto = objectMapper.readValue(request.getInputStream(), LoginDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.password());
        setDetails(request, authenticationToken);
//        return this.getAuthenticationManager().authenticate(authenticationToken);
        return this.getAuthenticationManager().authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        log.info("JwtAuthenticationFilter.successfulAuthentication");
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_MEMBER);
        CustomUserDetails customUserDetails = CustomUserDetails.of((String)authResult.getPrincipal(), "", roles);
        TokenDto tokenDto = jwtProvider.generateTokenDto(customUserDetails);
        String accessToken = tokenDto.accessToken();
        String refreshToken = tokenDto.refreshToken();
        String encryptedRefreshToken = aes128Config.encryptAes(refreshToken);
        jwtProvider.accessTokenSetHeader(accessToken, response);
        jwtProvider.refresshTokenSetHeader(encryptedRefreshToken, response);
        AuthMember findAuthMember = authMemberService.findAuthMember(customUserDetails.getEmail());
        Responder.loginSuccessResponse(response, findAuthMember);

        // 로그인 성공시 Refresh Token Redis 저장 ( key = Email / value = Refresh Token )
        long refreshTokenExpirationMillis = jwtProvider.getRefreshTokenExpirationMillis();
        redisService.setValues(findAuthMember.getEmail(), refreshToken, Duration.ofMillis(refreshTokenExpirationMillis));

        this.getSuccessHandler().onAuthenticationSuccess(request, response, authResult);
    }
}
