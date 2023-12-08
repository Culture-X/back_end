package TripAmi.backend.auth.authmember.service;


import TripAmi.backend.auth.authmember.service.dto.PasswordAuth;
import TripAmi.backend.auth.authmember.service.dto.RefreshTokenAuth;
import TripAmi.backend.auth.jwt.domain.AuthToken;
import TripAmi.backend.web.api.member.request.SignupMemberRequest;

import java.util.UUID;

import static TripAmi.backend.auth.authmember.service.dto.ConfirmTokenDto.ConfirmTokenRequest;

public interface AuthMemberService {

    /**
     * 이메일 중복 확인, 중복 시 에러 발생
     *
     * @param email 검증할 이메일
     */
    public void checkEmailDuplication(String email);
//
//    /**
//     * 가입 요청. 가입 요청시 6자리의 랜덤 코드를 이메일로 발송. (이메일 발송 생략에 대해서 논의 필요)
//     *
//     * @param request 멤버 생성 요청 DTO
//     * @return member id
//     */
//    String join(SignupMemberRequest request);
//
//    /**
//     * 가입 승인
//     *
//     * @param request 토큰 승인 요청 DTO
//     */
//    void joinConfirm(ConfirmTokenRequest request);
//
//    /**
//     * username and password authentication
//     *
//     * @param passwordAuth
//     * @return Jwt
//     */
//    AuthToken authenticate(PasswordAuth passwordAuth);
//
//    /**
//     * refresh token authentication
//     *
//     * @param refreshTokenAuth
//     * @return Jwt
//     */
//    AuthToken authenticate(RefreshTokenAuth refreshTokenAuth);

}
