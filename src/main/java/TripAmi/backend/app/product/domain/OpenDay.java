package TripAmi.backend.app.product.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    Integer fixedPeople;

    @ElementCollection
    @CollectionTable(name = "open_day_joined_persons", joinColumns = @JoinColumn(name = "open_day_id"))
    @Column(name = "person_id")
    List<Long> joinedPersonIds = new ArrayList<>();

    @Builder
    public OpenDay(Program program, LocalDateTime fixedDate) {
        this.program = program;
        this.fixedDate = fixedDate;
        this.fixedPeople = 0;
    }

    @SneakyThrows
    public void joinPerson(Long personId) {
        if (this.fixedPeople < program.getTotalPersonnel())
            fixedPeople++;
        else
            throw new RuntimeException("정원 초과되었습니다.");

        joinedPersonIds.add(personId);
    }
}
