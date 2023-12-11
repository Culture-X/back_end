package TripAmi.backend.web.api.program;


import TripAmi.backend.app.product.ProgramTheme;
import TripAmi.backend.app.product.domain.Program;
import TripAmi.backend.app.product.service.ProgramService;
import TripAmi.backend.web.api.common.GenericResponse;
import TripAmi.backend.web.api.program.request.CreateProgramRequest;
import TripAmi.backend.web.api.program.response.*;
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
    public void save(@RequestBody CreateProgramRequest request) {
        programService.save(request);
    }

    /**
     * 현재 Program들이 가지고 있는 Theme 전체 검색
     * 데이터가 있는 Theme만 불러와짐
     *
     * @return themes
     */

    @GetMapping("/{id}")
    public GenericResponse<ProgramDto> findById(@PathVariable Long id) {
        Program program = programService.findById(id);
        ProgramDto response = ProgramDto.builder()
                                  .title(program.getTitle())
                                  .amiId(program.getAmiId())
                                  .content(program.getContent())
                                  .images(program.getImages())
                                  .price(program.getPrice())
                                  .theme(program.getTheme())
                                  .build();
        return GenericResponse.ok(response);
    }

    /**
     * ToDo 현재 일반 프로그램 조회와 디테일 조회의 차이가 없음
     *
     * @param id program id
     * @return 해당하는 id의 프로그램 정보
     */
    @GetMapping("/{id}/detail")
    public GenericResponse<ProgramDetailDto> findProgramDetail(@PathVariable Long id) {
        Program program = programService.findDetailById(id);
        ProgramDetailDto response = ProgramDetailDto.builder()
                                        .title(program.getTitle())
                                        .amiId(program.getAmiId())
                                        .content(program.getContent())
                                        .images(program.getImages())
                                        .price(program.getPrice())
                                        .theme(program.getTheme())
                                        .build();

        return GenericResponse.ok(response);
    }

    @GetMapping("/themes")
    public GenericResponse<ThemeListResponse> findThemes() {
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
    public GenericResponse<ProgramListResponse> findProgramByTheme(@PathVariable String theme) {
        ProgramTheme programTheme = ProgramTheme.valueOf(theme.toUpperCase());
        List<ProgramDto> result = programService.findByTheme(programTheme);
        ProgramListResponse response = new ProgramListResponse(result);
        return GenericResponse.ok(response);
    }


    /**
     * ToDo
     * 기간 별 조회
     * FAQ 생성
     * FAQ 목록 조회
     * 잔여 인원 조회
     */
}
