package TripAmi.backend.web.api.auth.requset;

import javax.validation.constraints.Email;

import java.time.LocalDateTime;

public record AuthenticateEmailRequest(
    @Email(message = "이메일 형식이 올바르지 않습니다.") String email
) {
}
