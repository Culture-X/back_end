package TripAmi.backend.web.api.program;


import TripAmi.backend.app.product.service.ProgramService;
import TripAmi.backend.web.api.program.request.CreateProgramRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("‘api/v1/programs’")
@RequiredArgsConstructor
public class ProgramController {
    private final ProgramService programService;

    @PostMapping
    public void CreateProgram(@RequestBody CreateProgramRequest request) {
        programService.createProgram(request);
    }

    /**
     * 테마 목록 조회
     * 테마 별 조회
     * 기간 별 조회
     * 상세 조회
     * FAQ 생성
     * FAQ 목록 조회
     * 잔여 인원 조회
     */
}
