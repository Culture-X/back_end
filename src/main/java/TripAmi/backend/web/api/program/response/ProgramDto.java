package TripAmi.backend.web.api.program.response;

import TripAmi.backend.app.product.ProgramTheme;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ProgramDto {
    private String title;
    private List<String> images;
    private String content;
    private Integer price;
    private Long amiId;
    private ProgramTheme theme;
    private List<SpotDto> spots;

    @Builder
    public ProgramDto(String title, List<String> images, String content, Integer price, Long amiId, ProgramTheme theme, List<SpotDto> spots) {
        this.title = title;
        this.images = images;
        this.content = content;
        this.price = price;
        this.amiId = amiId;
        this.theme = theme;
        this.spots = spots;
    }

    public void setSpots(List<SpotDto> spots) {
        this.spots = spots;
    }
}