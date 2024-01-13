package TripAmi.backend.app.review.domain.event;

import TripAmi.backend.app.util.Star;
import lombok.Getter;

@Getter
public class ReviewStarUpdatedEvent {
    private Long amiId;
    private Star star;

    public ReviewStarUpdatedEvent(Long amiId, Star star) {
        this.amiId = amiId;
        this.star = star;
    }
}
