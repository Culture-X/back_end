package TripAmi.backend.web.api.faq.request;

import lombok.Builder;

public record FaqUpdateRequest(String question, String answer) {
    @Builder
    public FaqUpdateRequest {
    }
}
