package TripAmi.backend.auth.authmember.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Condition {
    @Column(nullable = false)
    private Boolean agreedMarketing;
    @LastModifiedDate
    private LocalDateTime agreedMarketingAt;

    public Condition(Boolean agreedMarketing) {
        this.agreedMarketing = agreedMarketing;
    }

    public void updateAgreedMarketing(Boolean agreed) {
        this.agreedMarketing = agreed;
    }
}
