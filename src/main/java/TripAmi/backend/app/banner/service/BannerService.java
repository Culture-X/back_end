package TripAmi.backend.app.banner.service;

import TripAmi.backend.web.api.banner.response.BannerDto;

import java.util.List;

public interface BannerService {
    void join(String title, String imgUrl);

    BannerDto findById(Long id);

    List<BannerDto> findBanners();

    BannerDto update(Long id, String title, String imgUrl);

    void delete(Long id);
}
