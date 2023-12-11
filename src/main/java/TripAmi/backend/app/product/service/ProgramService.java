package TripAmi.backend.app.product.service;

import TripAmi.backend.app.product.ProgramTheme;
import TripAmi.backend.app.product.domain.Program;
import TripAmi.backend.web.api.program.request.CreateProgramRequest;
import TripAmi.backend.web.api.program.response.ProgramDto;

import java.util.List;

public interface ProgramService {
    void save(CreateProgramRequest request);

    Program findById(Long id);

    List<Program> findByTitle(String title);

    List<ProgramDto> findByTheme(ProgramTheme theme);

    Program findDetailById(Long id);
}
