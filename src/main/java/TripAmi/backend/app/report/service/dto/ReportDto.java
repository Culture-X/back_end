package TripAmi.backend.app.report.service.dto;

import TripAmi.backend.app.report.domain.ReportType;
import lombok.Builder;

public record ReportDto(Long id, Long authorId, ReportType type, String content ) {

    @Builder
    public ReportDto {
    }
}
