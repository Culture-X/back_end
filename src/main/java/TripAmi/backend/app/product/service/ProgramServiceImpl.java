package TripAmi.backend.app.product.service;

import TripAmi.backend.app.product.domain.Program;
import TripAmi.backend.app.product.domain.ProgramRepository;
import TripAmi.backend.web.api.program.request.CreateProgramRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProgramServiceImpl implements ProgramService {

    private final ProgramRepository programRepository;

    @Override
    public void createProgram(CreateProgramRequest request) {
        Program program = Program.builder()
                              .title(request.title())
                              .images(request.images())
                              .content(request.content())
                              .price(request.price())
                              .totalPersonnel(request.totalPersonnel())
                              .theme(request.theme())
                              .keyWords(request.keyWords())
                              .spots(request.spots())
                              .location(request.location())
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
}
