package TripAmi.backend.web.api.banner.response;


import lombok.Builder;

public record BannerDto(Long id, String title, String imgUrl) {

    @Builder
    public BannerDto {
    }
}
