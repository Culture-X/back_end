package TripAmi.backend.web.api.faq.request;

public record CreateFaqRequest(
    String question,
    String answer
) {
}
