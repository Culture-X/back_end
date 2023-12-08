package TripAmi.backend.app.member.domain;

import TripAmi.backend.app.util.BaseEntity;
import TripAmi.backend.app.util.Image;
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
            name = "idx__member__nick_name",
            columnList = "nick_name",
            unique = true
        )
    }
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "member_id")
    private Long id;

    @Column(nullable = false, name = "nick_name")
    private String nickName;

    @Column(nullable = false, name = "img_url")
    private String imgUrl;

    @Column(nullable = false, name = "recieve_mail")
    private Boolean agreedMailNotification;

    @Column(nullable = false, name = "recieve_push")
    private Boolean agreedPushNotification;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ami_id")
    private Ami ami;

    @Embedded
    private BaseEntity baseEntity = new BaseEntity();

    @Builder
    public Member(String nickName, String imgUrl, Boolean agreedMailNotification, Boolean agreedPushNotification) {
        this.nickName = nickName;
        this.imgUrl = imgUrl;
        this.agreedMailNotification = agreedMailNotification;
        this.agreedPushNotification = agreedPushNotification;
    }
}
