package TripAmi.backend.web.api.program.response;

import TripAmi.backend.app.product.domain.TransportCode;
import lombok.Builder;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

@Builder
public record SpotDto(
    Long id,
    String title,
    String imgUrl,
    String content,
    LocalTime requiredTime,

    String distance,
    Map<TransportCode, LocalTime> transportWithTimes

) {
}
