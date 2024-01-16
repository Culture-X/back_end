package TripAmi.backend.web.api.report.response;

import TripAmi.backend.app.report.domain.ReportStatus;
import TripAmi.backend.app.report.domain.ReportType;
import lombok.Builder;

@Builder
public record ReportResponseDto(
    Long id,
    String content,
    Long author_id,
    ReportStatus status,
    ReportType type
) {
}
