package TripAmi.backend.web.api.member.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;

public record CreateMemberRequest(
    @NotEmpty String username,
    @NotEmpty @Email(message = "이메일 형식이 올바르지 않습니다.") String email,
    @NotEmpty @Size(min = 10, message = "비밀번호는 최소 10자 이상이어야 합니다.") String password) {

        public CreateMemberRequest(String username, String email, String password) {
            this.username = username;
            this.email = email;
            this.password = password;
        }

}
