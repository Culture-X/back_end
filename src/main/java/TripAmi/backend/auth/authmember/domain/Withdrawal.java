package TripAmi.backend.auth.authmember.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "withdrawal")
@Getter
public class Withdrawal {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "withdrawal_id")
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auth_member_id")
    private AuthMember authMember;
    @Column(nullable = false)
    private String reason;

    @Builder
    public Withdrawal(AuthMember authMember, String reason) {
        this.authMember = authMember;
        this.reason = reason;
    }
}
