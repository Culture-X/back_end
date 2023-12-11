package TripAmi.backend.app.faq.service;

import TripAmi.backend.web.api.faq.request.CreateFaqRequest;
import TripAmi.backend.web.api.faq.response.FaqDto;

import java.util.List;

public interface FaqService {
    void save(CreateFaqRequest request);

    FaqDto findById(Long id);

    List<FaqDto> findAll();
}
