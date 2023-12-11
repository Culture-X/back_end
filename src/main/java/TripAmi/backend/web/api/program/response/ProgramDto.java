package TripAmi.backend.web.api.program.response;

import TripAmi.backend.app.product.ProgramTheme;

import java.util.List;

public record ProgramDto(
    String title,
    List<String> images,
    String content,
    Integer price,
    Long amiId,
    ProgramTheme theme

) {
}
