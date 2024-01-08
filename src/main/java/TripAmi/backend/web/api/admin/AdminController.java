package TripAmi.backend.web.api.admin;

import TripAmi.backend.web.api.auth.response.FindMembersResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {

    // 회원 목록 조회
    @GetMapping("/members")
    public FindMembersResponse findMembers() {
        return FindMembersResponse.builder().build();
    }

}
