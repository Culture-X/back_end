package TripAmi.backend.app.faq.infra;

import TripAmi.backend.app.faq.domain.Faq;
import TripAmi.backend.app.faq.domain.FaqRepository;
import TripAmi.backend.app.faq.exception.FaqNotFound;
import TripAmi.backend.app.faq.service.FaqService;
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
    public void save(String question, String answer) {
        Faq faq = Faq.builder()
                      .question(question)
                      .answer(answer)
                      .build();
        faqRepository.save(faq);
    }

    @Override
    public FaqDto findById(Long id) {
        Faq item = faqRepository.findById(id).orElseThrow(FaqNotFound::new);
        return FaqDto.builder()
                   .question(item.getQuestion())
                   .answer(item.getAnswer())
                   .build();
    }

    @Override
    public List<FaqDto> findFaqs() {
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
    public FaqDto update(Long faqId, String question, String answer) {
        Faq faq = faqRepository.findById(faqId).orElseThrow(FaqNotFound::new);
        faq.update(question, answer);
        return FaqDto.builder()
                   .question(question)
                   .answer(answer)
                   .build();
    }
}
