package TripAmi.backend.auth.authmember.service.dto;

import TripAmi.backend.auth.jwt.service.dto.TokenDto;
import lombok.Builder;

public record AuthMemberDto(
    String email,
    String nickname
) {
    @Builder
    public AuthMemberDto(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }
}
