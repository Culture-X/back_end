package TripAmi.backend.app.reservation.domain;

import TripAmi.backend.app.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Reservation {
    @Id
    @Column(name = "reservation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    private Program program

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Reservation(Member member) {
        this.member = member;
    }
}
