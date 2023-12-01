package TripAmi.backend.auth.jwt.domain;

public record AuthToken(
    String accessToken,
    String refreshToken
) {

}
