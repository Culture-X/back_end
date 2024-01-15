package TripAmi.backend.web.api.program.request;

import TripAmi.backend.app.product.ProgramTheme;
import TripAmi.backend.app.product.domain.Spot;

import java.time.LocalDateTime;
import java.util.List;

public record CreateProgramRequest(
    Long amiId,
    String title,

    String subtitle,
    String content,
    String profileImgUrl,
    List<String> images,
    Integer price,
    Integer totalPeople,
    ProgramTheme theme,
    List<String> keyWords,
    List<Spot> spots,
    String location,
    LocalDateTime startTime

) {
}
