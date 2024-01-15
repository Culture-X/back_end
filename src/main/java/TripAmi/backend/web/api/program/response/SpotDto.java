package TripAmi.backend.web.api.program.response;

import lombok.Builder;

import java.time.LocalTime;

@Builder
public record SpotDto(
    Long id,
    String title,
    String imgUrl,
    String content,
    LocalTime requiredTime
    ) {
}
