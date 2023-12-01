package TripAmi.backend.app.product;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    Time requiredTime;

    @Builder
    public Spot(
        Program program,
        String title,
        String imgUrl,
        String content,
        Time requiredTime) {
        this.program = program;
        this.title = title;
        this.imgUrl = imgUrl;
        this.content = content;
        this.requiredTime = requiredTime;
    }
}
