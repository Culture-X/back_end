package TripAmi.backend.app.report.infra;

import TripAmi.backend.app.report.domain.Report;
import TripAmi.backend.app.report.domain.ReportRepository;
import TripAmi.backend.app.report.domain.ReportStatus;
import TripAmi.backend.app.report.domain.ReportType;
import TripAmi.backend.app.report.exception.ReportNotFound;
import TripAmi.backend.app.report.service.ReportService;
import TripAmi.backend.app.report.service.dto.ReportDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    @Override
    public void join(ReportType type, String content, Long authorId) {
        Report report = Report.builder()
                            .type(type)
                            .content(content)
                            .authorId(authorId)
                            .build();

        reportRepository.save(report);
    }

    @Override
    public void reportStatusUpdate(Long id, ReportStatus status) {
        Report report = reportRepository.findById(id).orElseThrow(ReportNotFound::new);

        report.updateStatus(status);
    }

    @Override
    public void delete(Long id) {
        Report report = reportRepository.findById(id).orElseThrow(ReportNotFound::new);

        report.delete();
    }

    @Override
    public List<ReportDto> findAll() {
        List<Report> reports = reportRepository.findAll();

        return reports.stream()
                   .map(report -> ReportDto.builder()
                                      .id(report.getId())
                                      .authorId(report.getAuthorId())
                                      .content(report.getContent())
                                      .type(report.getType())
                                      .build())
                   .collect(Collectors.toList());
    }
}
