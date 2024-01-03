package TripAmi.backend.app.reservation.domain;

import TripAmi.backend.app.member.domain.Traveler;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "reservation")
@Getter
public class Reservation {
    @Id
    @Column(name = "reservation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    private Program program

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Traveler traveler;

    @Builder
    public Reservation(Traveler traveler) {
        this.traveler = traveler;
    }
}
