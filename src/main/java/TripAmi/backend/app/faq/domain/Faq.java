package TripAmi.backend.app.faq.domain;

import TripAmi.backend.app.util.BaseEntity;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Table(name = "faq")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Faq {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "faq_id")
    private Long id;

    private String question;

    private String answer;

    @Embedded
    private BaseEntity baseEntity = new BaseEntity();

    @Builder
    public Faq(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public void update(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public void delete() {
        this.baseEntity.delete();
    }
}
