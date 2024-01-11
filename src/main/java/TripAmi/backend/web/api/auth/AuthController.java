package TripAmi.backend.web.api.auth;

import TripAmi.backend.auth.authmember.service.AuthCodeService;
import TripAmi.backend.auth.authmember.service.AuthMemberService;
import TripAmi.backend.auth.authmember.service.dto.LoginDto;
import TripAmi.backend.auth.security.JwtProvider;
import TripAmi.backend.web.api.auth.requset.*;
import TripAmi.backend.web.api.common.GenericResponse;
import TripAmi.backend.web.api.member.request.LoginRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@Tag(name = "Auth")
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthMemberService authMemberService;
    private final AuthCodeService authCodeService;
    private final JwtProvider jwtProvider;

    @Operation(summary = "이메일 중복 검사")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "유니크 이메일"),
        @ApiResponse(responseCode = "400", description = "중복된 이메일")
    })
    @PostMapping("/check/email")
    public GenericResponse<String> authenticateEmail(@RequestBody @Valid AuthenticateEmailRequest request) {
        authMemberService.validateUniqueEmail(request.email());
        return GenericResponse.ok();
    }

    @Operation(summary = "인증코드 이메일 전송")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "메일 전송 완료"),
        @ApiResponse(responseCode = "500", description = "메일 전송 실패")
    })
    @PostMapping("/send-mail")
    public GenericResponse<String> sendAuthenticationCode(@RequestBody @Valid AuthenticateEmailRequest request) {
        authMemberService.authenticateEmail(request.email(), LocalDateTime.now());
        return GenericResponse.ok();
    }

    @Operation(summary = "닉네임 중복 검사")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "유니크 닉네임"),
        @ApiResponse(responseCode = "400", description = "중복된 이메일")
    })
    @PostMapping("/check/nickname")
    public GenericResponse<String> validateNickname(@RequestBody @Valid ValidateNickNameRequest request) {
        authMemberService.validateUniqueNickname(request.nickName());
        return GenericResponse.ok();
    }

    @Operation(summary = "비밀번호 유효성 검사")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "유효한 비밀번호"),
        @ApiResponse(responseCode = "400", description = "유효하지 않은 비밀번호")
    })
    @PostMapping("/check/password")
    public GenericResponse<String> validatePassword(@RequestBody @Valid ValidatePasswordRequest request) {
        authMemberService.validatePasswordPattern(request.password());
        return GenericResponse.ok();
    }

    @Operation(summary = "이메일 인증코드 확인")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "코드 일치"),
        @ApiResponse(responseCode = "400", description = "코드 불일치, 인증시간 만료")
    })
    @PostMapping("/join/confirm")
    public GenericResponse<String> confirmAuthCode(@RequestBody @Valid ConfirmAuthCodeRequest request) {
        authCodeService.confirm(request.email(), request.inputCode(), LocalDateTime.now());
        return GenericResponse.ok();
    }

    @Operation(summary = "최종 회원가입")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "가입 완료"),
        @ApiResponse(responseCode = "400", description = "가입 실패")
    })
    @PostMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    public GenericResponse<String> join(@RequestBody @Valid JoinRequest request) {
        authMemberService.join(request.email(), request.nickname(), request.password(), request.agreedMarketing());
        return GenericResponse.ok();
    }

    @Operation(summary = "회원 가입 여부 확인")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "유니크 닉네임"),
        @ApiResponse(responseCode = "400", description = "중복된 이메일")
    })
    @PostMapping("/check/member")
    public GenericResponse<String> checkRegisteredMember(@RequestBody @Valid AuthenticateEmailRequest request) {
        authMemberService.findAuthMember(request.email());
        return GenericResponse.ok();
    }

    @Operation(summary = "로그인")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "토큰 발급"),
        @ApiResponse(responseCode = "401", description = "로그인 실패")
    })
    @PostMapping("/login")
    public GenericResponse<LoginDto> login(@RequestBody @Valid LoginRequest request) {
        return GenericResponse.ok(authMemberService.login(request.email(), request.password()));
    }

    @Operation(summary = "로그아웃")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "로그아웃 완료"),
        @ApiResponse(responseCode = "403", description = "권한 없음")
    })
    @PatchMapping("/logout")
    public GenericResponse<String> logout( HttpServletRequest request) {
        String encryptedRefreshToken = jwtProvider.resolveRefreshToken(request);
        String accessToken = jwtProvider.resolveAccessToken(request);
        authMemberService.logout(encryptedRefreshToken, accessToken);

        return GenericResponse.ok();
    }

    @Operation(summary = "토큰 재발급")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "access token 재발급 완료"),
        @ApiResponse(responseCode = "401", description = "refresh token 만료")
    })
    @PatchMapping("/token/reissue")
    public GenericResponse<String> reissue(HttpServletRequest request,
                                           HttpServletResponse response) {
        String encryptedRefreshToken = jwtProvider.resolveRefreshToken(request);
        String newAccessToken = authMemberService.reissueAccessToken(encryptedRefreshToken);
        jwtProvider.accessTokenSetHeader(newAccessToken, response);

        return GenericResponse.ok();
    }
}

