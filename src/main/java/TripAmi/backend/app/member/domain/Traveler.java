package TripAmi.backend.app.member.domain;

import TripAmi.backend.auth.authmember.domain.AuthMember;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor()
@Table(name = "treveler")
public class Traveler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "member_id")
    private Long id;
    @OneToOne(mappedBy = "traveler")
    private AuthMember authMember;

    public void setAuthMember(AuthMember authMember) {
        this.authMember = authMember;
    }
}
