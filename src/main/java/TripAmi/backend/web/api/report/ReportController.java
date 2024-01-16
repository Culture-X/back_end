package TripAmi.backend.web.api.report;

import TripAmi.backend.app.report.service.ReportService;
import TripAmi.backend.app.report.service.dto.ReportDto;
import TripAmi.backend.web.api.common.GenericResponse;
import TripAmi.backend.web.api.report.request.CreateReportRequest;
import TripAmi.backend.web.api.response.EmptyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping
    public GenericResponse<List<ReportDto>> getAllReports() {
        return GenericResponse.ok(reportService.findAll());
    }

    @PostMapping
    public GenericResponse<EmptyResponse> save(@RequestBody CreateReportRequest request) {
        reportService.join(request.type(), request.content(), request.authorId());
        return GenericResponse.ok();
    }


}
