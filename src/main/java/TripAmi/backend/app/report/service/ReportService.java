package TripAmi.backend.app.report.service;

import TripAmi.backend.app.report.domain.ReportStatus;
import TripAmi.backend.app.report.domain.ReportType;
import TripAmi.backend.app.report.service.dto.ReportDto;

import java.util.List;

public interface ReportService {
    void join(ReportType type, String content, Long id);

    void reportStatusUpdate(Long id, ReportStatus status);

    void delete(Long id);

    List<ReportDto> findAll();
}
