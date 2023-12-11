package TripAmi.backend.app.product.service;

import TripAmi.backend.app.product.domain.Program;
import TripAmi.backend.web.api.program.request.CreateProgramRequest;

import java.util.List;

public interface ProgramService {
    void createProgram(CreateProgramRequest request);

    Program findById(Long id);

    List<Program> findByTitle(String title);
}
