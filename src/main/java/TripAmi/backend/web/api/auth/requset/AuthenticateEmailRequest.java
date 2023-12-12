package TripAmi.backend.web.api.auth.requset;

import jakarta.validation.constraints.Email;
import lombok.Builder;

public record AuthenticateEmailRequest(
    @Email(message = "이메일 형식이 올바르지 않습니다.") String email,
    String mailForm
) {
    @Builder
    public AuthenticateEmailRequest {
    }
}
