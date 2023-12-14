package TripAmi.backend.web.api.banner.response;

import lombok.Builder;

import java.util.List;

public record BannerListResponse(List<BannerDto> banners) {

    @Builder
    public BannerListResponse {
    }
}
