package TripAmi.backend.app.member.infra;

import TripAmi.backend.app.member.domain.TravelerRepository;
import TripAmi.backend.app.member.domain.Traveler;
import TripAmi.backend.app.member.service.TravelerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TravelerServiceImpl implements TravelerService {
    private final TravelerRepository travelerRepository;

    @Override
    @Transactional
    public Long save() {
        Traveler traveler = new Traveler();
        travelerRepository.save(traveler);
        return traveler.getId();
    }
}
