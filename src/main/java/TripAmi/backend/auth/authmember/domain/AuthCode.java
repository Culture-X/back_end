package TripAmi.backend.auth.authmember.domain;

import TripAmi.backend.auth.authmember.exception.AuthCodeMismatchException;
import TripAmi.backend.auth.authmember.service.exception.InputTimeOutException;
import javax.persistence.*;

import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
    name = "auth_code",
    indexes = {
        @Index(
            name = "idx__auth_code__email",
            columnList = "email",
            unique = true
        )
    }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auth_code_id", nullable = false)
    private Long id;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String code;
    private Boolean confirmed;
    private LocalDateTime validTime;

    @Builder
    public AuthCode(
        String email,
        String code,
        LocalDateTime requestTime,
        Long min
    ) {
        this.email = email;
        this.code = code;
        this.confirmed = false;
        this.validTime = requestTime.plusMinutes(min);
    }

    public void updateCode(String code, LocalDateTime requestTime, Long min) {
        this.code = code;
        this.validTime = requestTime.plusMinutes(min);
        this.confirmed = false;
    }

    public void validateInputTime(LocalDateTime input) {
        if (this.validTime.isBefore(input))
            throw new InputTimeOutException();
    }

    public void validateInputCode(String input) {
        if (!this.code.equals(input)) {
            throw new AuthCodeMismatchException();
        }
    }

    public void authCodeConfirm() {
        this.confirmed = true;
    }

    public Boolean isConfirmed() {
        return this.confirmed;
    }
}
