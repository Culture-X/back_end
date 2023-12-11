package TripAmi.backend.auth.security;

import org.springframework.security.core.Authentication;

/**
 * TripAmi 서비스에서 사용되는 커스텀 Authentication 인터페이스
 */
public interface TripAmiAuthentication extends Authentication {
    Long getMemberId();

    Long getAuthMemberId();

    String getUsername();
}
