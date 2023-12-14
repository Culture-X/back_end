package TripAmi.backend.web.api.banner;

import TripAmi.backend.app.banner.service.BannerService;
import TripAmi.backend.web.api.banner.request.BannerCreateRequest;
import TripAmi.backend.web.api.banner.request.BannerUpdateRequest;
import TripAmi.backend.web.api.banner.response.BannerDto;
import TripAmi.backend.web.api.banner.response.BannerListResponse;
import TripAmi.backend.web.api.common.GenericResponse;
import TripAmi.backend.web.api.response.EmptyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/banners")
@RequiredArgsConstructor
public class BannerController {
    private final BannerService bannerService;

    @PostMapping
    public GenericResponse<BannerDto> save(@RequestBody BannerCreateRequest request) {
        bannerService.join(request.title(), request.imgUrl());
        return GenericResponse.ok(BannerDto.builder()
                                      .title(request.title())
                                      .imgUrl(request.imgUrl())
                                      .build());
    }

    @GetMapping("/{id}")
    public GenericResponse<BannerDto> findById(@PathVariable Long id) {
        return GenericResponse.ok(bannerService.findById(id));
    }

    @GetMapping
    public GenericResponse<BannerListResponse> findAll() {
        List<BannerDto> banners = bannerService.findAll();
        BannerListResponse response = BannerListResponse.builder()
                                          .banners(banners)
                                          .build();
        return GenericResponse.ok(response);
    }

    @PutMapping("/{id}")
    public GenericResponse<BannerDto> update(@PathVariable Long id, @RequestBody BannerUpdateRequest request) {
        return GenericResponse.ok(bannerService.update(id, request.title(), request.imgUrl()));
    }

    @DeleteMapping("/{id}")
    public GenericResponse<EmptyResponse> delete(@PathVariable Long id) {
        bannerService.findById(id);
        return GenericResponse.ok();
    }
}
