package TripAmi.backend.auth.authmember.domain;

import TripAmi.backend.app.member.domain.Ami;
import TripAmi.backend.app.member.domain.Traveler;
import TripAmi.backend.app.util.BaseEntity;
import TripAmi.backend.auth.authmember.infra.RolesConverter;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(
    name = "auth_member",
    indexes = {
        @Index(
            name = "idx__auth_member__email",
            columnList = "email",
            unique = true
        ),
        @Index(name = "idx__auth_member__status",
            columnList = "status")
    }
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class AuthMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auth_member_id", nullable = false)
    private Long id;
    @Email
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String nickname;
    @Column(nullable = false)
    private String password;
    private String imgUrl;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "traveler_id")
    private Traveler traveler;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ami_id")
    private Ami ami;
    @Convert(converter = RolesConverter.class)
    private Set<Role> roles = new TreeSet<>();
    @Embedded
    private Condition condition;
    @Enumerated(value = EnumType.STRING)
    private MemberStatus status;
    @Embedded
    private BaseEntity baseEntity;

    @Builder
    public AuthMember(
        String email,
        String raw,
        String nickname,
        Role role,
        Traveler traveler,
        Ami ami,
        Boolean agreedMarketing,
        PasswordEncoder passwordEncoder
    ) {
        this.email = email;
        this.password = passwordEncoder.encode(raw);
        this.nickname = nickname;
        this.condition = new Condition(agreedMarketing);
        this.status = MemberStatus.ACTIVE;
        addRole(role);
        updateMember(traveler, ami);
        this.baseEntity = new BaseEntity();
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    private void updateMember(Traveler traveler, Ami ami) {
        this.traveler = traveler;
        this.ami = ami;
        traveler.setAuthMember(this);
        ami.setAuthMember(this);
    }

    public void switchRole(Role role) {
        switch (role) {
            case ROLE_AMI: {
                roles.remove(Role.ROLE_TRAVELER);
                roles.add(Role.ROLE_AMI);
                break;
            }
            case ROLE_TRAVELER: {
                roles.remove(Role.ROLE_AMI);
                roles.add(Role.ROLE_TRAVELER);
            }
        }
    }

    /**
     * 입력받은 비밀번호의 유효성을 검사한 후에 올바르면 업데이트하는 메서드
     *
     * @param rawPassword
     * @param passwordEncoder
     */
    public void updatePassword(String rawPassword,
                               PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(rawPassword);
    }

    /**
     * 회원 탈퇴 시 상태를 deleted로 변경
     */
    public void delete() {
        this.status = MemberStatus.WITHDRAWAL;
        baseEntity.delete();
    }

    public void updateStatus(MemberStatus status) {
        if (!this.baseEntity.getDeleted())
            this.status = status;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updateAgreedMarketing(Boolean agreed) {
        this.condition.updateAgreedMarketing(agreed);
    }

    public Boolean isWithdrawal() {
        return this.status.equals(MemberStatus.WITHDRAWAL);
    }
}
