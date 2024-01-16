package TripAmi.backend.web.api.review.request;

import TripAmi.backend.app.util.Star;

public record ReviewUpdateRequest (
   Long reviewId,
   Long authorId,
   Star star,
   String content
) {
}
