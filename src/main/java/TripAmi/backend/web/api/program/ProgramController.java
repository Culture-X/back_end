package TripAmi.backend.web.api.program;


import TripAmi.backend.app.product.ProgramTheme;
import TripAmi.backend.app.product.service.ProgramService;
import TripAmi.backend.web.api.common.GenericResponse;
import TripAmi.backend.web.api.program.request.CreateProgramRequest;
import TripAmi.backend.web.api.program.response.ProgramDto;
import TripAmi.backend.web.api.program.response.ProgramListResponse;
import TripAmi.backend.web.api.program.response.ThemeDto;
import TripAmi.backend.web.api.program.response.ThemeListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("‘api/v1/programs’")
@RequiredArgsConstructor
public class ProgramController {
    private final ProgramService programService;

    /**
     * 프로그램 생성 url
     * 필요 데이터를 가지고 메소드 호출시 생성 (return은 없습니다.)
     *
     * @param request 생성 DTO (CreateProgramRequest)
     */
    @PostMapping
    public void createProgram(@RequestBody CreateProgramRequest request) {
        programService.createProgram(request);
    }

    /**
     * 현재 Program들이 가지고 있는 Theme 전체 검색
     * 데이터가 있는 Theme만 불러와짐
     *
     * @return themes
     */
    @GetMapping("/themes")
    public GenericResponse<ThemeListResponse> getThemes() {
        List<ThemeDto> result = Arrays.stream(ProgramTheme.values())
                                    .map(theme -> new ThemeDto(theme.toString()))
                                    .collect(Collectors.toList());

        ThemeListResponse response = new ThemeListResponse(result);
        return GenericResponse.ok(response);
    }

    /**
     * 파람으로 던져지는 Theme에 해당하는 프로그램들을 리턴해줌
     * 없는 Theme 던져지면 Exception
     *
     * @param theme 테마 (String)
     * @return 해당하는 Theme에 포함된 Program
     */
    @GetMapping("/{theme}")
    public GenericResponse<ProgramListResponse> getProgramByTheme(@PathVariable String theme) {
        ProgramTheme programTheme = ProgramTheme.valueOf(theme.toUpperCase());
        List<ProgramDto> result = programService.findByTheme(programTheme);
        ProgramListResponse response = new ProgramListResponse(result);
        return GenericResponse.ok(response);
    }
    /**
     * ToDo
     * 기간 별 조회
     * 상세 조회
     * FAQ 생성
     * FAQ 목록 조회
     * 잔여 인원 조회
     */
}
