package TripAmi.backend.auth.authmember.service.dto;

import TripAmi.backend.auth.authmember.domain.MemberStatus;

public record AuthMemberDto(
    String email,
    String nickname,
    String imgUrl,
    Boolean agreedMarketing,
    MemberStatus memberStatus

) {
}
