package TripAmi.backend.auth.authmember.domain;

import TripAmi.backend.app.util.BaseEntity;
import TripAmi.backend.auth.authmember.exception.PasswordMismatchException;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(
    name = "auth_member",
    indexes = {
        @Index(
            name = "idx__auth_member__email",
            columnList = "email",
            unique = true
        )
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
    private String password;
    @Embedded
    private BaseEntity baseEntity;

    @Builder
    public AuthMember(
        String email,
        String raw,
        PasswordHasher hasher
    ) {
        this.email = email;
        this.password = hasher.hash(raw);
        this.baseEntity = new BaseEntity();
    }

    /**
     * 패스워드 수정 시, 이전 패스워드와 동일하지 않은지 검증함
     *
     * @param rawPassword 수정할 패스워드
     * @param hasher      패스워드 해셔
     */
    public void verifyPassword(String rawPassword, PasswordHasher hasher) {
        if (!hasher.isMatch(rawPassword, password)) {
            throw new PasswordMismatchException();
        }
    }

    /**
     * 입력받은 비밀번호의 유효성을 검사한 후에 올바르면 업데이트하는 메서드
     *
     * @param rawPassword
     * @param hasher
     */
    public void updatePassword(String rawPassword, PasswordValidator validator,
                               PasswordHasher hasher) {
        validator.validate(this, rawPassword, hasher);
        this.password = hasher.hash(rawPassword);
    }

    /**
     * 회원 탈퇴 시 상태를 deleted로 변경
     */
    public void delete() {
        baseEntity.delete();
    }
}
