package TripAmi.backend.web.api.auth.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginResponse {
    private String email;
    private String nickname;
    private String imgUrl;

    @Builder
    public LoginResponse(String email, String nickname, String imgUrl) {
        this.email = email;
        this.nickname = nickname;
        this.imgUrl = imgUrl;
    }
}
