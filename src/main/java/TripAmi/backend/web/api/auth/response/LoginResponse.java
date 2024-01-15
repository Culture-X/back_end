package TripAmi.backend.web.api.auth.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginResponse {
    private Long authMemberid;
    private String email;
    private String nickname;
    private String imgUrl;

    @Builder
    public LoginResponse(Long authMemberid, String email, String nickname, String imgUrl) {
        this.authMemberid = authMemberid;
        this.email = email;
        this.nickname = nickname;
        this.imgUrl = imgUrl;
    }
}
