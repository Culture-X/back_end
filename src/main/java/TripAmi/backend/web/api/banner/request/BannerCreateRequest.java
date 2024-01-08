package TripAmi.backend.web.api.banner.request;

import lombok.Builder;

public record BannerCreateRequest(String title, String imgUrl) {
    @Builder
    public BannerCreateRequest {
    }
}
