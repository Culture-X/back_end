package TripAmi.backend.app.faq.service;

import TripAmi.backend.app.faq.domain.Faq;
import TripAmi.backend.app.faq.domain.FaqRepository;
import TripAmi.backend.web.api.faq.request.FaqCreateRequest;
import TripAmi.backend.web.api.faq.request.FaqUpdateRequest;
import TripAmi.backend.web.api.faq.response.FaqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FaqServiceImpl implements FaqService {
    private final FaqRepository faqRepository;

    @Override
    @Transactional
    public void save(FaqCreateRequest request) {
        Faq faq = Faq.builder()
                      .question(request.question())
                      .answer(request.answer())
                      .build();
        faqRepository.save(faq);
    }

    @Override
    public FaqDto findById(Long id) {
        Faq item = faqRepository.findById(id).orElseThrow();
        return FaqDto.builder()
                   .question(item.getQuestion())
                   .answer(item.getAnswer())
                   .build();
    }

    @Override
    public List<FaqDto> findAll() {
        List<Faq> allFaq = faqRepository.findAll();
        return allFaq.stream()
                   .map(item -> new FaqDto(
                       item.getQuestion(),
                       item.getAnswer()
                   ))
                   .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public FaqDto updateFaq(Long faqId, FaqUpdateRequest request) {
        Faq faq = faqRepository.findById(faqId).orElseThrow();
        faq.update(request.question(), request.answer());
        return FaqDto.builder()
                   .question(request.question())
                   .answer(request.answer())
                   .build();
    }
}
