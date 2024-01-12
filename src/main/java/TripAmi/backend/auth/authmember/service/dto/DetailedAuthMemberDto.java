package TripAmi.backend.auth.authmember.service.dto;

import TripAmi.backend.auth.authmember.domain.MemberStatus;
import lombok.Builder;

import java.time.LocalDateTime;

public record DetailedAuthMemberDto(
    String email,
    String nickname,
    String imgUrl,
    Boolean agreedMarketing,
    MemberStatus memberStatus,
    LocalDateTime joinedAt,
    Long amiId,
    Long travelerId
) {
    @Builder
    public DetailedAuthMemberDto(String email, String nickname, String imgUrl, Boolean agreedMarketing, MemberStatus memberStatus, LocalDateTime joinedAt, Long amiId, Long travelerId) {
        this.email = email;
        this.nickname = nickname;
        this.imgUrl = imgUrl;
        this.agreedMarketing = agreedMarketing;
        this.memberStatus = memberStatus;
        this.joinedAt = joinedAt;
        this.amiId = amiId;
        this.travelerId = travelerId;
    }
}
