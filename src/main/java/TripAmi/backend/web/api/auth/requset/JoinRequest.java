package TripAmi.backend.web.api.auth.requset;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record JoinRequest(
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    String email,
    @Size(max = 10)
    @NotBlank
    String nickName,
    String password
) {
}
