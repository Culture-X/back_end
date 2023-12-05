package TripAmi.backend.app.member.domain;

import TripAmi.backend.app.util.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

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
    private String username;

    @Column(nullable = false, name = "user_email")
    private String userEmail;

    @Column(nullable = false, name = "recieve_mail")
    private Boolean recieveMail;

    @Column(nullable = false, name = "recieve_push")
    private Boolean recievePush;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "createdAt", column = @Column(name = "created_at")),
        @AttributeOverride(name = "updatedAt", column = @Column(name = "updated_at")),
        @AttributeOverride(name = "deleted", column = @Column(name = "member_deleted"))
    })
    private BaseEntity baseEntity = new BaseEntity();

    public Member(UUID userSeq, String username, String userEmail, Boolean recieveMail, Boolean recievePush) {
        this.userSeq = userSeq;
        this.username = username;
        this.userEmail = userEmail;
        this.recieveMail = recieveMail;
        this.recievePush = recievePush;
    }

    public UUID updateUuid(UUID uuid) {
        this.userSeq = uuid;
        return uuid;
    }
}
