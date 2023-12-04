package TripAmi.backend.app.banner.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

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

    @CreatedDate
    @Column(nullable = false, name = "create_at")
    private LocalDateTime createdAt;

    @Builder
    public Banner(String imgUrl, String title, LocalDateTime createdAt) {
        this.imgUrl = imgUrl;
        this.title = title;
        this.createdAt = createdAt;
    }
}
