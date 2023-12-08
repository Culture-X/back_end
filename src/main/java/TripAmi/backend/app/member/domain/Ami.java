package TripAmi.backend.app.member.domain;

import TripAmi.backend.app.util.Language;
import TripAmi.backend.app.util.Star;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ami {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ami_id")
    private Long id;

    @OneToOne(mappedBy = "ami")
    private Member member;

    @Column(name = "profile_img_url", nullable = false)
    private String profileImgUrl;

    @Column(nullable = false)
    private Set<Language> languages;

    @Embedded
    private Rating rating;
    private String introduce;
    private String strength;

    @Builder
    public Ami(
        Member member,
        String profileImgUrl,
        Set<Language> languages,
        Rating rating,
        String introduce,
        String strength
    ) {
        this.member = member;
        this.profileImgUrl = profileImgUrl;
        this.languages = languages;
        this.rating = rating;
        this.introduce = introduce;
        this.strength = strength;
    }

    /**
     * 별점 별 개수를 업데이트
     *
     * @param stars 별점
     */
    void updateRating(List<Star> stars) {
        for (Star star : stars) {
            rating.addStar(star);
        }
    }

    /**
     * profile image url을 수정
     *
     * @param profileImgUrl 변경할 image url
     */
    void updateProfileImg(String profileImgUrl) {
        this.profileImgUrl = profileImgUrl;
    }
}
