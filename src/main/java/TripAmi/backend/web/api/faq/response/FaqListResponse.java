package TripAmi.backend.web.api.faq.response;

import lombok.Builder;

import java.util.List;

public record FaqListResponse(
    List<FaqDto> faqs

) {
    @Builder
    public FaqListResponse {
    }
}
