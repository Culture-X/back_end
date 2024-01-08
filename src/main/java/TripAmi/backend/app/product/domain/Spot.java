package TripAmi.backend.app.product.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "spot")
@Getter
public class Spot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "spot_id")
    Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id")
    Program program;
    @Column(nullable = false, columnDefinition = "VARCHAR(20)")
    String title;
    @Column(nullable = false)
    String imgUrl;
    @Column(nullable = false)
    String content;
    @Column(nullable = false)
    LocalTime requiredTime;

    @Builder
    public Spot(
        Program program,
        String title,
        String imgUrl,
        String content,
        LocalTime requiredTime) {
        this.program = program;
        this.title = title;
        this.imgUrl = imgUrl;
        this.content = content;
        this.requiredTime = requiredTime;
    }
}
