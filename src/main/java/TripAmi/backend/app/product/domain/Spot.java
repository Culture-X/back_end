package TripAmi.backend.app.product.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

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

    @ElementCollection
    @CollectionTable(name = "spot_transport_mapping", joinColumns = @JoinColumn(name = "spot_id"))
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "transport_time")
    Map<TransportCode, LocalDateTime> transportWithTimes = new HashMap<>();

    @Builder
    public Spot(Program program, String title, String imgUrl, String content, LocalTime requiredTime, Map<TransportCode, LocalDateTime> transportWithTimes) {
        this.program = program;
        this.title = title;
        this.imgUrl = imgUrl;
        this.content = content;
        this.requiredTime = requiredTime;
        this.transportWithTimes = transportWithTimes;
    }

    public void setProgram(Program program) {
        this.program = program;
    }
}
