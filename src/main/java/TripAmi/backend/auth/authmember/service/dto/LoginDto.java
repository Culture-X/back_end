package TripAmi.backend.auth.authmember.service.dto;

import lombok.Builder;

public record LoginDto(
//    Long memberId,
    String email,
//    String nickname,
//    String imgUrl
    String password
) {
//    @Builder
//    public LoginDto(Long memberId, String email, String nickname, String imgUrl) {
//        this.memberId = memberId;
//        this.email = email;
//        this.nickname = nickname;
//        this.imgUrl = imgUrl;
//    }

    @Builder
    public LoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
