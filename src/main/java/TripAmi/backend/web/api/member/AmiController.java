package TripAmi.backend.web.api.member;

import TripAmi.backend.app.member.domain.Ami;
import TripAmi.backend.app.member.service.AmiService;
import TripAmi.backend.app.member.service.dto.AmiDto;
import TripAmi.backend.auth.authmember.domain.AuthMember;
import TripAmi.backend.auth.authmember.service.AuthMemberService;
import TripAmi.backend.web.api.common.GenericResponse;
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

    @GetMapping("/{amiId}")
    public GenericResponse<AmiDto> findAmi(@PathVariable Long amiId) {
        return GenericResponse.ok(amiService.findAmiById(amiId));
    }

//    @GetMapping("/{email}")
//    public GenericResponse<AmiDto> findAmi(@PathVariable String email) {
//        return GenericResponse.ok(amiService.findAmiByEmail(email));
//    }

    @GetMapping
    public GenericResponse<List<AmiDto>> findAmis() {
        return GenericResponse.ok(amiService.findAmis());
    }
}
