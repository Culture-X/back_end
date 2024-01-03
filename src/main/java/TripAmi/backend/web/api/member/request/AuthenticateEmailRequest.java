package TripAmi.backend.web.api.member.request;

import javax.validation.constraints.Email;

public record AuthenticateEmailRequest(@Email String email) {
}
