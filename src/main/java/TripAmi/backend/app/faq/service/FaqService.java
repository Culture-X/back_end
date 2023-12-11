package TripAmi.backend.app.faq.service;

import TripAmi.backend.app.faq.domain.Faq;

import java.util.List;

public interface FaqService {
    void save(String question, String answer);

    Faq findById(Long id);

    List<Faq> findAll();
}
