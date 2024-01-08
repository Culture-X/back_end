package TripAmi.backend.auth.authmember.service.dto;

import lombok.Builder;

import javax.validation.constraints.NotNull;

public record UpdateNicknameRequest(
    @NotNull
    String nickname) {
    @Builder
    public UpdateNicknameRequest(String nickname) {
        this.nickname = nickname;
    }
}
