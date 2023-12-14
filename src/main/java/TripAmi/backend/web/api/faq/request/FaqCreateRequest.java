package TripAmi.backend.web.api.faq.request;

public record FaqCreateRequest(
    String question,
    String answer
) {
}
