package TripAmi.backend.auth.authmember.service.dto;

import TripAmi.backend.auth.jwt.service.dto.TokenDto;
import lombok.Builder;

public record LoginDto(
    Long memberId,
    String email,
    String nickname,
    String imgUrl
) {
    @Builder
    public LoginDto(Long memberId, String email, String nickname, String imgUrl) {
        this.memberId = memberId;
        this.email = email;
        this.nickname = nickname;
        this.imgUrl = imgUrl;
    }
}
