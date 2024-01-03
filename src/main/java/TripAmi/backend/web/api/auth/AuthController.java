package TripAmi.backend.web.api.auth;

import TripAmi.backend.auth.authmember.service.AuthCodeService;
import TripAmi.backend.auth.authmember.service.AuthMemberService;
import TripAmi.backend.auth.authmember.service.dto.LoginDto;
import TripAmi.backend.auth.security.JwtProvider;
import TripAmi.backend.web.api.auth.requset.*;
import TripAmi.backend.web.api.common.GenericResponse;
import TripAmi.backend.web.api.member.request.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class AuthController {

    private final AuthMemberService authMemberService;
    private final AuthCodeService authCodeService;
    private final JwtProvider jwtProvider;

    @PostMapping("/join/email")
    public GenericResponse<String> authenticateEmail(@RequestBody @Valid AuthenticateEmailRequest request) {
        authMemberService.authenticateEmail(request.email(), LocalDateTime.now());
        return GenericResponse.ok();
    }

    @GetMapping("/join/nickname")
    public GenericResponse<String> validateNickname(@RequestBody @Valid ValidateNickNameRequest request) {
        authMemberService.validateUniqueNickname(request.nickName());
        return GenericResponse.ok();
    }

    @GetMapping("/join/password")
    public GenericResponse<String> validatePassword(@RequestBody @Valid ValidatePasswordRequest request) {
        authMemberService.validatePasswordPattern(request.password());
        return GenericResponse.ok();
    }

    @GetMapping("/join/confirm")
    public GenericResponse<String> confirmAuthCode(@RequestBody @Valid ConfirmAuthCodeRequest request) {
        authCodeService.confirm(request.email(), request.inputCode(), LocalDateTime.now());
        return GenericResponse.ok();
    }

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    public GenericResponse<String> join(@RequestBody @Valid JoinRequest request) {
        authMemberService.join(request.email(), request.nickname(), request.password(), request.agreedMarketing());
        return GenericResponse.ok();
    }

    @GetMapping("/login/find")
    public GenericResponse<String> findAccount(@RequestBody @Valid AuthenticateEmailRequest request) {
        authMemberService.findAccount(request.email(), LocalDateTime.now());
        return GenericResponse.ok();
    }

    @PostMapping("/login")
    public GenericResponse<LoginDto> login(@RequestBody @Valid LoginRequest request) {
        return GenericResponse.ok(authMemberService.login(request.email(), request.password()));
    }

    @PatchMapping("/logout")
    public GenericResponse<String> logout( HttpServletRequest request) {
        String encryptedRefreshToken = jwtProvider.resolveRefreshToken(request);
        String accessToken = jwtProvider.resolveAccessToken(request);
        authMemberService.logout(encryptedRefreshToken, accessToken);

        return GenericResponse.ok();
    }

    @PatchMapping("/reissue")
    public GenericResponse<String> reissue(HttpServletRequest request,
                                           HttpServletResponse response) {
        String encryptedRefreshToken = jwtProvider.resolveRefreshToken(request);
        String newAccessToken = authMemberService.reissueAccessToken(encryptedRefreshToken);
        jwtProvider.accessTokenSetHeader(newAccessToken, response);

        return GenericResponse.ok();
    }
}

