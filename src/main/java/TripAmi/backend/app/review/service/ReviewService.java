package TripAmi.backend.app.review.service;

import TripAmi.backend.app.member.domain.Traveler;
import TripAmi.backend.app.reservation.domain.Reservation;
import TripAmi.backend.app.review.domain.Review;
import TripAmi.backend.app.review.service.dto.ReviewDto;
import TripAmi.backend.app.util.Star;

import java.util.List;

public interface ReviewService {
    Long save(Long travelerId, Long reservationId, Star star, String content);
    ReviewDto findReviewById(Long reviewId);
    List<ReviewDto> findReviewByProgramId(Long programId);
    void updateReviewContent(Long reviewId ,String content);
    void updateReviewStar(Long reviewId, Star star);
    void delete(Long reviewId);
    void report(Long reporterId, Long reviewId);
}
