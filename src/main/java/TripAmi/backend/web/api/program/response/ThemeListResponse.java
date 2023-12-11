package TripAmi.backend.web.api.program.response;

import lombok.Builder;

import java.util.List;

public record ThemeListResponse(List<ThemeDto> themes) {
    @Builder
    public ThemeListResponse {
    }
}
