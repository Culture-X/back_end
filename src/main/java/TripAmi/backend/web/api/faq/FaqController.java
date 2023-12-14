package TripAmi.backend.web.api.faq;

import TripAmi.backend.app.faq.service.FaqService;
import TripAmi.backend.web.api.common.GenericResponse;
import TripAmi.backend.web.api.faq.request.FaqCreateRequest;
import TripAmi.backend.web.api.faq.request.FaqUpdateRequest;
import TripAmi.backend.web.api.faq.response.FaqDto;
import TripAmi.backend.web.api.faq.response.FaqListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/faqs")
@RequiredArgsConstructor
public class FaqController {
    private final FaqService faqService;

    @PostMapping
    public void save(@RequestBody FaqCreateRequest request) {
        faqService.save(request.question(), request.answer());
    }

    @GetMapping
    public GenericResponse<FaqListResponse> findAll() {
        List<FaqDto> result = faqService.findFaqs();
        FaqListResponse response = FaqListResponse.builder()
                                       .faqs(result)
                                       .build();
        return GenericResponse.ok(response);
    }

    @GetMapping("/{id}")
    public GenericResponse<FaqDto> findById(@PathVariable Long id) {
        return GenericResponse.ok(faqService.findById(id));
    }

    @PostMapping("/{id}")
    public GenericResponse<FaqDto> updateFaq(@PathVariable Long id, @RequestBody FaqUpdateRequest request) {
        return GenericResponse.ok(faqService.update(id, request.question(), request.answer()));
    }
}
