package TripAmi.backend.web.api.faq.response;

import lombok.Builder;

public record FaqDto(
    String question,
    String answer
) {
    @Builder
    public FaqDto {
    }
}
