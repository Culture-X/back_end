package WanderBuddy.backend.app.member.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Table(
    name = "member",
    indexes = {
        @Index(
            name = "idx__member__username",
            columnList = "username",
            unique = true
        )
    }
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "member_type")
public abstract class Member {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID userSeq;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String userEmail;


    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at", nullable = false)
    private LocalDateTime deletedAt;

    @Column(nullable = false)
    private Boolean recieveMail;

    @Column(nullable = false)
    private Boolean recievePush;
}
