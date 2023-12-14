package TripAmi.backend.app.faq.service;

import TripAmi.backend.web.api.faq.request.FaqCreateRequest;
import TripAmi.backend.web.api.faq.request.FaqUpdateRequest;
import TripAmi.backend.web.api.faq.response.FaqDto;

import java.util.List;

public interface FaqService {
    void save(String question, String answer);

    FaqDto findById(Long id);

    List<FaqDto> findAll();

    FaqDto updateFaq(Long faqId, String question, String answer);
}
