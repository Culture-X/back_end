package TripAmi.backend.web.api.member;

import TripAmi.backend.auth.authmember.service.AuthMemberService;
import TripAmi.backend.auth.authmember.service.dto.DetailedAuthMemberDto;
import TripAmi.backend.auth.security.JwtProvider;
import TripAmi.backend.web.api.auth.requset.DeleteMemberRequest;
import TripAmi.backend.web.api.auth.requset.UpdatePasswordRequest;
import TripAmi.backend.web.api.common.GenericResponse;
import TripAmi.backend.web.api.member.request.SelectRoleRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {
    private final AuthMemberService authMemberService;
    private final JwtProvider jwtProvider;


    @Operation(summary = "회원 상태 선택")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "변경 완료"),
    })
    @PatchMapping("/{memberId}/role")
    @PreAuthorize("idMatches(#memberId) or hasRole('ROLE_ADMIN')")
    //todo 권한 설정
    public GenericResponse<String> selectRole(@PathVariable Long memberId, @RequestBody @Valid SelectRoleRequest request) {
        return GenericResponse.ok();
    }

    @Operation(summary = "비밀번호 변경")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "변경 완료"),
    })
    // 비밀번호 변경 컨트롤러(이메일, 비밀번호)
    @PatchMapping("/{memberId}/password")
    @PreAuthorize("idMatches(#memberId) or hasRole('ROLE_ADMIN')")
    public GenericResponse<Object> updatePassword(@PathVariable Long memberId, @RequestBody @Valid UpdatePasswordRequest request) {
        authMemberService.updatePassword(memberId, request.password());
        return GenericResponse.ok();
    }

    @Operation(summary = "회원 탈퇴")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "탈퇴 완료"),
    })
    @PostMapping("/{memberId}/withdrawal")
    @PreAuthorize("idMatches(#memberId) or hasRole('ROLE_ADMIN')")
    public GenericResponse<Object> deleteMember(@PathVariable Long memberId, @RequestBody @Valid DeleteMemberRequest request) {
        authMemberService.withdrawal(memberId, request.reason());
        return GenericResponse.ok();
    }

    // 회원 상세 조회
    @Operation(summary = "회원 상세 조회")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "탈퇴 완료"),
    })
    @GetMapping("/{memberId}")
    @PreAuthorize("idMatches(#memberId) or hasRole('ROLE_ADMIN')")
    public GenericResponse<DetailedAuthMemberDto> findMember(@PathVariable Long memberId) {
        return GenericResponse.ok(authMemberService.findAuthMemberById(memberId));
    }

    @Operation(summary = "회원 상세 목록 조회")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "탈퇴 완료"),
    })
    @GetMapping()
    public GenericResponse<List<DetailedAuthMemberDto>> findMembers() {
        return GenericResponse.ok(authMemberService.findAuthMembers());
    }

    @Operation(summary = "로그아웃")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "로그아웃 완료"),
        @ApiResponse(responseCode = "403", description = "권한 없음")
    })
    @PatchMapping("/{memberId}/logout")
    @PreAuthorize("idMatches(#memberId)")
    public GenericResponse<String> logout(@PathVariable Long memberId, HttpServletRequest request) {
        String encryptedRefreshToken = jwtProvider.resolveRefreshToken(request);
        String accessToken = jwtProvider.resolveAccessToken(request);
        authMemberService.logout(encryptedRefreshToken, accessToken);

        return GenericResponse.ok();
    }

}
