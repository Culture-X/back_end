package TripAmi.backend.web.api.member.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record SignupResponse(UUID id) {
}
