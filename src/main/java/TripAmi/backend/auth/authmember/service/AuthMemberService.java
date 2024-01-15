package TripAmi.backend.auth.authmember.service;


import TripAmi.backend.app.member.domain.Ami;
import TripAmi.backend.app.member.domain.Traveler;
import TripAmi.backend.auth.authmember.domain.AuthMember;
import TripAmi.backend.auth.authmember.domain.Role;
import TripAmi.backend.auth.authmember.service.dto.DetailedAuthMemberDto;

import java.time.LocalDateTime;
import java.util.List;

public interface AuthMemberService {

    public void validateUniqueEmail(String email);

    public void validateUniqueNickname(String nickname);

    public void validatePasswordPattern(String password);

    public void authenticateEmail(String email, LocalDateTime inputTime);

    public void join(String email, String nickname, String password, Boolean agreedMarketing);

    public void findAccount(String email, LocalDateTime inputTime);

    public void updatePassword(Long authMemberId, String password);

    void withdrawal(Long authMemberId, String reason);

//    LoginDto login(String email, String password);

    void logout(String encryptedRefreshToken, String accessToken);

    void selectRole(String email, Role role);

    AuthMember findAuthMemberByEmail(String email);

    DetailedAuthMemberDto findAuthMemberById(Long id);

    List<DetailedAuthMemberDto> findAuthMembers();

    DetailedAuthMemberDto findRegisteredMember(String email);

    void updateNickname(Long authMemberId, String nickname);

    String reissueAccessToken(String encryptedRefreshToken);

    Ami findAmiByEmail(String email);

    Traveler findTravelerByEmail(String email);

}
