package TripAmi.backend.web.api.program.request;

import TripAmi.backend.app.product.ProgramTheme;
import TripAmi.backend.app.product.domain.Spot;

import java.util.List;

public record CreateProgramRequest(
    Long amiId,
    String title,
    String content,
    String profileImgUrl,
    List<String> images,
    Integer price,
    Integer totalPersonnel,
    ProgramTheme theme,
    List<String> keyWords,
    List<Spot> spots,
    String location
) {
}
