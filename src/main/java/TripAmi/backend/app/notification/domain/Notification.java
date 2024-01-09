package TripAmi.backend.app.notification.domain;

import TripAmi.backend.app.util.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "notification")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "noti_id")
    private Long id;

    private String title;

    private String content;

    @Column(name = "is_read")
    private Boolean isRead;

    @Embedded
    private BaseEntity baseEntity = new BaseEntity();

    @Builder
    public Notification(String title, String content) {
        this.title = title;
        this.content = content;
        this.isRead = false;
    }
}
