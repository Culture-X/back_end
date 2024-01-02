package TripAmi.backend.app.product.service;

import TripAmi.backend.app.product.ProgramTheme;
import TripAmi.backend.app.product.domain.ProgramRepository;
import TripAmi.backend.web.api.program.request.CreateProgramRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

@SpringBootTest
public class ProgramServiceTest {

    @Mock
    private ProgramRepository programRepository;

    @InjectMocks
    private ProgramService programService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create_program() {
        // Arrange
        CreateProgramRequest request = new CreateProgramRequest(
            1L,
            "Program Title",
            "Program Content",
            "img_url",
            Collections.singletonList("image1.jpg"),
            100,
            10,
            ProgramTheme.ACTIVITY,
            Collections.singletonList("keyword"),
            Collections.emptyList(), // Assuming no spots for simplicity
            "Location"
        );

//        // Act
        programService.save(request);
//
//        // Assert
        Assertions.assertNotNull(programService.findByTitle("Program Title")); // Correct title
    }
}