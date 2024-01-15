package TripAmi.backend.app.review.domain;

import TripAmi.backend.app.member.domain.Traveler;
import TripAmi.backend.app.reservation.domain.Reservation;
import TripAmi.backend.app.review.domain.event.ReviewStarUpdatedEvent;
import TripAmi.backend.app.util.BaseEntity;
import TripAmi.backend.app.util.Star;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "review")
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Review {
    @Id
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "traveler_Id")
    private Traveler traveler;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    private Star star;

    private String content;

    private Boolean reported;

    @Embedded
    private BaseEntity baseEntity = new BaseEntity();

    @Builder
    public Review(Traveler traveler, Reservation reservation, Star star, String content) {
        this.traveler = traveler;
        this.reservation = reservation;
        this.star = star;
        this.content = content;
    }

    public void updateStar(Star star, ApplicationEventPublisher publisher) {
        this.star = star;
        publisher.publishEvent(new ReviewStarUpdatedEvent(this.reservation.getProgram().getAmi().getId(), star));
    }


    public void updateContent(String content) {
        this.content = content;
    }

    public void report() {
        this.reported = true;
    }

    public Boolean isReported() {
        return this.reported;
    }

    public void delete() {
        this.baseEntity.delete();
    }

    public Boolean isDeleted() {
        return this.baseEntity.getDeleted();
    }
}
