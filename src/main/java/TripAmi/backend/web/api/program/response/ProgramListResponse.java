package TripAmi.backend.web.api.program.response;

import TripAmi.backend.app.product.domain.Program;
import lombok.Builder;

import java.util.List;

public record ProgramListResponse(List<ProgramDto> programs) {
    @Builder
    public ProgramListResponse {
    }
}
