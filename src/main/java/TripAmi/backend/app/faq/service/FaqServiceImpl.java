package TripAmi.backend.app.faq.service;

import TripAmi.backend.app.faq.domain.Faq;
import TripAmi.backend.app.faq.domain.FaqRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public Faq findById(Long id) {
        return faqRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Faq> findAll() {
        return faqRepository.findAll();
    }
}
