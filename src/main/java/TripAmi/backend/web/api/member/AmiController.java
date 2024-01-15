package TripAmi.backend.web.api.member;

import TripAmi.backend.app.member.domain.Ami;
import TripAmi.backend.app.member.service.AmiService;
import TripAmi.backend.app.member.service.dto.AmiDto;
import TripAmi.backend.auth.authmember.domain.AuthMember;
import TripAmi.backend.auth.authmember.service.AuthMemberService;
import TripAmi.backend.web.api.common.GenericResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/amis")
public class AmiController {
    private final AmiService amiService;

    @Operation(summary = "아미 아이디로 아미 조회")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "조회 완료"),
    })
    @GetMapping("/{amiId}")
    public GenericResponse<AmiDto> findAmi(@PathVariable Long amiId) {
        return GenericResponse.ok(amiService.findAmiById(amiId));
    }

    @Operation(summary = "이메일로 아미 조회")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "조회 완료"),
    })
    @GetMapping("/{email}")
    public GenericResponse<AmiDto> findAmi(@PathVariable String email) {
        return GenericResponse.ok(amiService.findAmiByEmail(email));
    }

    @Operation(summary = "아미 목록 조회")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "조회 완료"),
    })
    @GetMapping
    public GenericResponse<List<AmiDto>> findAmis() {
        return GenericResponse.ok(amiService.findAmis());
    }
}
