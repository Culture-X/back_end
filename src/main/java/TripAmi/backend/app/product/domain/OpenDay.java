package TripAmi.backend.app.product.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "open_day")
public class OpenDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "open_day_id")
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id")
    Program program;

    @Column(nullable = false)
    LocalDateTime fixedDate;

    @Column(nullable = false)
    Integer count;

    @Builder
    public OpenDay(Program program, LocalDateTime fixedDate, Integer count) {
        this.program = program;
        this.fixedDate = fixedDate;
        this.count = count;
    }
}
