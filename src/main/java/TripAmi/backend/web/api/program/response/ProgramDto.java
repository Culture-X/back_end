package TripAmi.backend.web.api.program.response;

import TripAmi.backend.app.product.ProgramTheme;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class ProgramDto {
    private String title;
    private String subTitle;
    private List<String> images;
    private LocalDateTime startTime;
    private String content;
    private Integer price;
    private Long amiId;
    private ProgramTheme theme;
    private List<String> keywords;
    private List<SpotDto> spots;

    @Builder
    public ProgramDto(String title, String subTitle, List<String> images, LocalDateTime startTime, String content, Integer price, Long amiId, ProgramTheme theme, List<String> keywords, List<SpotDto> spots) {
        this.title = title;
        this.subTitle = subTitle;
        this.images = images;
        this.startTime = startTime;
        this.content = content;
        this.price = price;
        this.amiId = amiId;
        this.theme = theme;
        this.keywords = keywords;
        this.spots = spots;
    }

    public void setSpots(List<SpotDto> spots) {
        this.spots = spots;
    }
}