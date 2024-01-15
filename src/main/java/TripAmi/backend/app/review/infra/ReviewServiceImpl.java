package TripAmi.backend.app.review.infra;

import TripAmi.backend.app.member.domain.Traveler;
import TripAmi.backend.app.member.domain.TravelerRepository;
import TripAmi.backend.app.member.service.TravelerService;
import TripAmi.backend.app.member.service.dto.TravelerDto;
import TripAmi.backend.app.member.service.exception.MemberNotFound;
import TripAmi.backend.app.reservation.domain.Reservation;
import TripAmi.backend.app.reservation.domain.ReservationRepository;
import TripAmi.backend.app.reservation.service.ReservationNotFound;
import TripAmi.backend.app.review.domain.Review;
import TripAmi.backend.app.review.domain.ReviewRepository;
import TripAmi.backend.app.review.service.ReviewService;
import TripAmi.backend.app.review.service.dto.ReviewDto;
import TripAmi.backend.app.review.service.exception.ReviewNotFound;
import TripAmi.backend.app.util.Star;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final TravelerRepository travelerRepository;
    private final ReservationRepository reservationRepository;
    private final TravelerService travelerService;
    private final ApplicationEventPublisher publisher;

    @Override
    @Transactional
    public Long save(Long travelerId, Long reservationId, Star star, String content) {
        Traveler findTraveler = travelerRepository.findById(travelerId).orElseThrow(MemberNotFound::new);
        Reservation findReservation = reservationRepository.findById(reservationId).orElseThrow(ReservationNotFound::new);
        Review review = Review.builder()
                                  .traveler(findTraveler)
                                  .reservation(findReservation)
                                  .star(star)
                                  .content(content).build();
        reviewRepository.save(review);
        return review.getId();
    }

    @Override
    public ReviewDto findReviewById(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(ReviewNotFound::new);
        TravelerDto author = travelerService.findById(review.getTraveler().getId());
        return ReviewDto.builder()
                   .reviewId(review.getId())
                   .programId(review.getReservation().getProgram().getId())
                   .star(review.getStar())
                   .content(review.getContent())
                   .author(author)
                   .deleted(review.isDeleted())
                   .build();
    }

    @Override
    public List<ReviewDto> findReviewByProgramId(Long programId) {
        List<Review> reviews = reviewRepository.findAllByProgramId(programId);
        return reviews.stream().map(review -> ReviewDto.builder()
                                                  .reviewId(review.getId())
                                                  .programId(programId)
                                                  .star(review.getStar())
                                                  .content(review.getContent())
                                                  .author(travelerService.findById(review.getTraveler().getId()))
                                                  .deleted(review.isDeleted())
                                                  .build()).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateReviewContent(Long reviewId, String content) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(ReviewNotFound::new);
        review.updateContent(content);
    }

    @Override
    @Transactional
    public void updateReviewStar(Long reviewId, Star star) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(ReviewNotFound::new);
        review.updateStar(star, publisher);
    }

    @Override
    @Transactional
    public void delete(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(ReviewNotFound::new);
        review.delete();
    }

    @Override
    @Transactional
    public void report(Long reporterId, Long reviewId) {
        log.info(reporterId + " reported " + reporterId);
        Review review = reviewRepository.findById(reviewId).orElseThrow(ReviewNotFound::new);
        review.report();
    }
}
