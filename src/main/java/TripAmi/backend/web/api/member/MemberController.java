package TripAmi.backend.web.api.member;

import TripAmi.backend.auth.authmember.service.AuthMemberService;
import TripAmi.backend.auth.authmember.service.dto.AuthMemberDto;
import TripAmi.backend.auth.authmember.service.dto.UpdateNicknameRequest;
import TripAmi.backend.web.api.auth.requset.DeleteMemberRequest;
import TripAmi.backend.web.api.auth.requset.FindMembersRequest;
import TripAmi.backend.web.api.auth.requset.UpdatePasswordRequest;
import TripAmi.backend.web.api.auth.response.FindMembersResponse;
import TripAmi.backend.web.api.common.GenericResponse;
import TripAmi.backend.web.api.member.request.SelectRoleRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {
    private final AuthMemberService authMemberService;

    @Operation(summary = "회원 상태 선택")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "변경 완료"),
    })
    @PatchMapping("/{memberId}/role")
    @PreAuthorize("idMatches(#memberId) or hasRole('ROLE_ADMIN')")
    //todo 권한 설정
    public GenericResponse<AuthMemberDto> selectRole(@RequestBody @Valid SelectRoleRequest request) {
        return GenericResponse.ok(authMemberService.selectRole(request.email(), request.role()));
    }

    @Operation(summary = "비밀번호 변경")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "변경 완료"),
    })
    // 비밀번호 변경 컨트롤러(이메일, 비밀번호)
    @PatchMapping("/{memberId}/password")
    @PreAuthorize("idMatches(#memberId) or hasRole('ROLE_ADMIN')")
    public void updatePassword(@PathVariable Long memberId, @RequestBody @Valid UpdatePasswordRequest request) {
        authMemberService.updatePassword(memberId, request.password());
    }

    @Operation(summary = "회원 탈퇴")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "탈퇴 완료"),
    })
    @PatchMapping("/{memberId}/withdrawal")
    @PreAuthorize("idMatches(#memberId) or hasRole('ROLE_ADMIN')")
    public void deleteMember(@PathVariable Long memberId, @RequestBody @Valid DeleteMemberRequest request) {
        authMemberService.withdrawal(memberId, request.reason());
    }


    // 회원 상세 조회
    @Operation(summary = "회원 상세 조회")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "탈퇴 완료"),
    })
    @GetMapping("/{memberId}")
    @PreAuthorize("idMatches(#memberId) or hasRole('ROLE_ADMIN')")
    public void findMember(@RequestHeader("Authorization") String authorizationHeader, @PathVariable Long memberId) {
        // todo admin계정과 해당 유저만 조회 가능하도록
    }


    //
    @GetMapping("/filter")
    public FindMembersResponse findMembersByFilter(@RequestBody @Valid FindMembersRequest request) {
        return FindMembersResponse.builder().build();
    }

}
