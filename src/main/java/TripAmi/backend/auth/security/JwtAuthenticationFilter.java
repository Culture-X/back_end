package TripAmi.backend.auth.security;

import TripAmi.backend.app.util.Responder;
import TripAmi.backend.auth.authmember.domain.AuthMember;
import TripAmi.backend.auth.authmember.service.AuthMemberService;
import TripAmi.backend.auth.authmember.service.dto.LoginDto;
import TripAmi.backend.auth.jwt.service.RedisService;
import TripAmi.backend.auth.jwt.service.dto.TokenDto;
import TripAmi.backend.auth.security.infra.CustomUserDetails;
import TripAmi.backend.config.AES128Config;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;

// jwt가 유효성을 검증하는 필터
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final AES128Config aes128Config;
    private final AuthMemberService authMemberService;
    private final RedisService redisService;

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        // ServletInputStream을 LoginDto 객체로 역직렬화
        ObjectMapper objectMapper = new ObjectMapper();
        LoginDto loginDto = objectMapper.readValue(request.getInputStream(), LoginDto.class);
        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto);

        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        CustomUserDetails customUserDetails = (CustomUserDetails) authResult.getPrincipal();
        TokenDto tokenDto = jwtProvider.generateTokenDto(customUserDetails);
        String accessToken = tokenDto.accessToken();
        String refreshToken = tokenDto.refreshToken();
        String encryptedRefreshToken = aes128Config.encryptAes(refreshToken);
        jwtProvider.accessTokenSetHeader(accessToken, response);
        jwtProvider.refresshTokenSetHeader(encryptedRefreshToken, response);
        AuthMember findAuthMember = authMemberService.findAuthMember(customUserDetails.getId());
        Responder.loginSuccessResponse(response, findAuthMember);

        // 로그인 성공시 Refresh Token Redis 저장 ( key = Email / value = Refresh Token )
        long refreshTokenExpirationMillis = jwtProvider.getRefreshTokenExpirationMillis();
        redisService.setValues(findAuthMember.getEmail(), refreshToken, Duration.ofMillis(refreshTokenExpirationMillis));

        this.getSuccessHandler().onAuthenticationSuccess(request, response, authResult);
    }
}
