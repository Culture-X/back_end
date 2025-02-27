package TripAmi.backend.app.product.infra;

import TripAmi.backend.app.member.domain.Ami;
import TripAmi.backend.app.member.domain.AmiRepository;
import TripAmi.backend.app.member.service.AmiService;
import TripAmi.backend.app.product.ProgramTheme;
import TripAmi.backend.app.product.domain.Program;
import TripAmi.backend.app.product.domain.ProgramRepository;
import TripAmi.backend.app.product.service.ProgramService;
import TripAmi.backend.web.api.program.request.CreateProgramRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProgramServiceImpl implements ProgramService {

    private final ProgramRepository programRepository;
    private final AmiService amiService;

    @Override
    @Transactional
    public void save(CreateProgramRequest request) {
        Ami ami = amiService.findNonWithdrawalAmiById(request.amiId());
        Program program = Program.builder()
                              .ami(ami)
                              .title(request.title())
                              .subTitle(request.subtitle())
                              .images(request.images())
                              .content(request.content())
                              .price(request.price())
                              .totalPeople(request.totalPeople())
                              .theme(request.theme())
                              .keywords(request.keyWords())
                              .spots(request.spots())
                              .location(request.location())
                              .startTime(request.startTime())
                              .build();
        programRepository.save(program);
    }

    @Override
    public Program findById(Long id) {
        return programRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Program> findByTitle(String title) {
        return programRepository.findByTitle(title);
    }

    @Override
    public List<Program> findByTheme(ProgramTheme theme) {
        return programRepository.findByTheme(theme);
    }

    @Override
    public Program findDetailById(Long id) {
        return programRepository.findById(id).orElseThrow();
    }

}
