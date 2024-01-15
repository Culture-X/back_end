package TripAmi.backend.app.review.service.dto;

import TripAmi.backend.app.member.service.dto.TravelerDto;
import TripAmi.backend.app.util.Star;
import lombok.Builder;

public record ReviewDto(
    Long reviewId,
    Long programId,
    TravelerDto author,
    Star star,
    String content,
    Boolean deleted
) {
    @Builder
    public ReviewDto(Long reviewId, Long programId, TravelerDto author, Star star, String content, Boolean deleted) {
        this.reviewId = reviewId;
        this.programId = programId;
        this.author = author;
        this.star = star;
        this.content = content;
        this.deleted = deleted;
    }
}
