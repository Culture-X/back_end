package TripAmi.backend.app.banner.infra;

import TripAmi.backend.app.banner.domain.Banner;
import TripAmi.backend.app.banner.domain.BannerRepository;
import TripAmi.backend.app.banner.exception.BannerNotFound;
import TripAmi.backend.app.banner.service.BannerService;
import TripAmi.backend.web.api.banner.response.BannerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BannerServiceImpl implements BannerService {
    private final BannerRepository bannerRepository;

    @Override
    @Transactional
    public void join(String title, String imgUrl) {
        Banner banner = Banner.builder()
                            .title(title)
                            .imgUrl(imgUrl)
                            .build();
        bannerRepository.save(banner);
    }

    @Override
    public BannerDto findById(Long id) {
        Banner findOne = bannerRepository.findById(id).orElseThrow(BannerNotFound::new);
        return new BannerDto(id, findOne.getTitle(), findOne.getImgUrl());
    }

    @Override
    public List<BannerDto> findBanners() {
        List<Banner> items = bannerRepository.findAll();
        return items.stream()
                   .map(item -> new BannerDto(
                       item.getId(),
                       item.getTitle(),
                       item.getImgUrl()
                   )).collect(Collectors.toList());
    }

    @Override
    public BannerDto update(Long id, String title, String imgUrl) {
        Banner banner = bannerRepository.findById(id).orElseThrow(BannerNotFound::new);
        banner.update(title, imgUrl);
        return BannerDto.builder()
                   .title(title)
                   .imgUrl(imgUrl)
                   .build();
    }

    @Override
    public void delete(Long id) {
        Banner banner = bannerRepository.findById(id).orElseThrow(BannerNotFound::new);
        banner.delete();
    }

}
