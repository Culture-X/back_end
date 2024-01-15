package TripAmi.backend.web.api.auth.response;

import TripAmi.backend.auth.authmember.domain.AuthMember;
import TripAmi.backend.auth.authmember.domain.Role;

import java.util.Set;

public class JoinResponse{
    private Long memberId;
    private String email;
    private String nickname;
    private Set<Role> roles;
    private String token;

    public JoinResponse(AuthMember authMember) {
        this.memberId = authMember.getId();
        this.email = authMember.getEmail();
        this.nickname = authMember.getNickname();
        this.roles = authMember.getRoles();
    }
}
