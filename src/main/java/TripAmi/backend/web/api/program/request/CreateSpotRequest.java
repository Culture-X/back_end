package TripAmi.backend.web.api.program.request;

import java.time.LocalTime;

public record CreateSpotRequest(
    String title,
    String imgUrl,
    String content,
    LocalTime requiredTime
) {
}
