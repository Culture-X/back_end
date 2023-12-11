package TripAmi.backend.auth.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JWT Authentication 생성 이후에 실행되어야함.(Bearer Token 인증 필터 이후)
 * JwtAuthenticationToken 을 TripAmiJwtAuthenticationToken 으로 대체함.
 */
public class TripAmiJwtFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof JwtAuthenticationToken) {
            SecurityContextHolder.getContext().setAuthentication(new TripAmiJwtAuthenticationToken((JwtAuthenticationToken) authentication));
        }
        filterChain.doFilter(request, response);
    }
}
