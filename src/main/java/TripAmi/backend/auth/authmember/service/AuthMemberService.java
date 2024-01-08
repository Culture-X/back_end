package TripAmi.backend.auth.authmember.service;


import TripAmi.backend.auth.authmember.domain.AuthMember;
import TripAmi.backend.auth.authmember.domain.Role;
import TripAmi.backend.auth.authmember.service.dto.AuthMemberDto;
import TripAmi.backend.auth.authmember.service.dto.LoginDto;
import TripAmi.backend.auth.jwt.service.dto.TokenDto;

import java.time.LocalDateTime;

public interface AuthMemberService {

    public void validateUniqueEmail(String email);

    public void validateUniqueNickname(String nickname);

    public void validatePasswordPattern(String password);

    public void authenticateEmail(String email, LocalDateTime inputTime);

    public void join(String email, String nickname, String password, Boolean agreedMarketing);

    public void findAccount(String email, LocalDateTime inputTime);

    public void updatePassword(Long authMemberId, String password);

    void withdrawal(Long authMemberId, String reason);

    LoginDto login(String email, String password);

    void logout(String encryptedRefreshToken, String accessToken);

    AuthMemberDto selectRole(String email, Role role);

    AuthMember findAuthMember(String email);

    AuthMember findAuthMember(Long id);

    void updateNickname(Long authMemberId, String nickname);

    String reissueAccessToken(String encryptedRefreshToken);

}
