package TripAmi.backend.app.member.service.dto;

import TripAmi.backend.auth.authmember.domain.AuthCode;
import lombok.Builder;

import java.time.LocalDateTime;

public record MemberDto(
    String email,
    String nickname

) {
    @Builder
    public MemberDto {
    }
}
