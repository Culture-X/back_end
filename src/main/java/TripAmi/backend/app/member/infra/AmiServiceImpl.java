package TripAmi.backend.app.member.infra;

import TripAmi.backend.app.member.domain.Ami;
import TripAmi.backend.app.member.domain.AmiRepository;
import TripAmi.backend.app.member.service.AmiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AmiServiceImpl implements AmiService {

    private final AmiRepository amiRepository;

    @Override
    @Transactional
    public Long save() {
        Ami ami = new Ami();
        amiRepository.save(ami);
        return ami.getId();
    }
}
