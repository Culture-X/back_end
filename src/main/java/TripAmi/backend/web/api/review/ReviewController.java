package TripAmi.backend.web.api.review;

import TripAmi.backend.app.review.service.ReviewService;
import TripAmi.backend.app.review.service.dto.ReviewDto;
import TripAmi.backend.web.api.common.GenericResponse;
import TripAmi.backend.web.api.response.EmptyResponse;
import TripAmi.backend.web.api.review.request.ReviewUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/{id}")
    public GenericResponse<ReviewDto> findById(@PathVariable Long id) {
        return GenericResponse.ok(reviewService.findReviewById(id));
    }

    @GetMapping()
    public GenericResponse<List<ReviewDto>> findByProgramId(@RequestBody Long programId) {
        return GenericResponse.ok(reviewService.findReviewByProgramId(programId));
    }

    @DeleteMapping("/{reviewId}")
    @PreAuthorize("idMatches(#memberId()) or hasRole('ROLE_ADMIN')")
    public GenericResponse<EmptyResponse> deleteReview(@PathVariable Long reviewId, @RequestBody Long authorId ) {
        reviewService.delete(reviewId);
        return GenericResponse.ok();
    }

    @PostMapping("/{reviewId}")
    @PreAuthorize("idMatches(#memberId()) or hasRole('ROLE_ADMIN')")
    public GenericResponse<EmptyResponse> updateReview(@RequestBody ReviewUpdateRequest request) {
        if (request.star() != null)
            reviewService.updateReviewStar(request.reviewId(), request.star());
        if (request.content() != null)
            reviewService.updateReviewContent(request.reviewId(), request.content());
        return GenericResponse.ok();
    }


}
