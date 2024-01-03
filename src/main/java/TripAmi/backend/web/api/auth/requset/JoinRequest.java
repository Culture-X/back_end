package TripAmi.backend.web.api.auth.requset;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record JoinRequest(
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    String email,
    @Size(max = 10)
    @NotBlank
    String nickname,
    String password,
    Boolean agreedMarketing
) {
}
