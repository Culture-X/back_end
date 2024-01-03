package TripAmi.backend.auth.jwt.service.dto;

import lombok.Builder;

@Builder
public record TokenDto(
    String grantType,
    String authorizationType,
    String accessToken,
    String refreshToken,
    Long accessTokenExpiresIn
) {
}
