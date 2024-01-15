package TripAmi.backend.app.member.service.dto;

import lombok.Builder;

public record TravelerDto(
    Long id,
    String email,
    String nickname,
    String imgUrl
) {
    @Builder
    public TravelerDto(Long id, String email, String nickname, String imgUrl) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.imgUrl = imgUrl;
    }
}
