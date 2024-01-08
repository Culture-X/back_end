package TripAmi.backend.app.banner.domain;

import TripAmi.backend.app.util.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "banner")
@Getter
public class Banner {
    @Id
    @Column(name = "banner_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "img_url")
    private String imgUrl;

    private String title;

    @Embedded
    private BaseEntity baseEntity = new BaseEntity();

    @Builder
    public Banner(String imgUrl, String title) {
        this.imgUrl = imgUrl;
        this.title = title;
    }

    public void update(String title, String imgUrl) {
        this.title = title;
        this.imgUrl = imgUrl;
    }

    public void delete() {
        this.baseEntity.delete();
    }
}
