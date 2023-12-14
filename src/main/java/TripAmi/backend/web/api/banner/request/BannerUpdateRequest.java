package TripAmi.backend.web.api.banner.request;

import lombok.Builder;

public record BannerUpdateRequest(String title, String imgUrl) {
    @Builder
    public BannerUpdateRequest {
    }
}
