package TripAmi.backend.web.api.member;

import TripAmi.backend.app.member.service.MemberService;
import TripAmi.backend.web.api.common.GenericResponse;
import TripAmi.backend.web.api.common.StatusCode;
import TripAmi.backend.web.api.member.request.UpdateLunchRecommendationConsent;
import TripAmi.backend.web.api.member.response.UpdateUserSettingResponse;
import beforespring.yourfood.app.member.service.MemberService;
import beforespring.yourfood.web.api.common.GenericResponse;
import beforespring.yourfood.web.api.common.StatusCode;
import beforespring.yourfood.web.api.member.request.UpdateLocationRequest;
import beforespring.yourfood.web.api.member.request.UpdateLunchRecommendationConsent;
import beforespring.yourfood.web.api.member.response.UpdateUserSettingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    /**
     * 회원 점심 추천 기능 동의 설정 업데이트
     *
     * @param updateLunchRecommendationConsent 업데이트 정보
     */
    @PatchMapping("/lunch-recommendation-consent")
    public GenericResponse<UpdateUserSettingResponse> updateLunchRecommendation(@RequestBody UpdateLunchRecommendationConsent updateLunchRecommendationConsent) {
        memberService.updateLunchRecommendationConsent(updateLunchRecommendationConsent.lunchRecommendationConsent(), updateLunchRecommendationConsent.memberId());
        return GenericResponse.<UpdateUserSettingResponse>builder()
            .statusCode(StatusCode.CREATED)
            .message("Success")
            .build();
    }

    /**
     * 회원 위치 업데이트
     *
     * @param updateLocationRequest 업데이트 정보
     */
    @PatchMapping("/location")
    public GenericResponse<UpdateUserSettingResponse> updateLocation(@RequestBody UpdateLocationRequest updateLocationRequest) {
        memberService.updateLocation(updateLocationRequest.lat(), updateLocationRequest.lon(), updateLocationRequest.memberId());
        return GenericResponse.<UpdateUserSettingResponse>builder()
            .statusCode(StatusCode.CREATED)
            .message("Success")
            .build();
    }

}
