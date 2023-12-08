package TripAmi.backend.web.api.member.request;

import jakarta.validation.constraints.Email;

public record AuthenticateEmailRequest(@Email String email) {
}
