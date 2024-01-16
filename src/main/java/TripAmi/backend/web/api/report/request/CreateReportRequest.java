package TripAmi.backend.web.api.report.request;

import TripAmi.backend.app.report.domain.ReportType;

public record CreateReportRequest (
    Long authorId,
    String content,
    ReportType type
) {
}
