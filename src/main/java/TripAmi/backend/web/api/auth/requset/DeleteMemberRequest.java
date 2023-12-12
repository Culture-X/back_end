package TripAmi.backend.web.api.auth.requset;

import TripAmi.backend.app.member.domain.Member;
import jakarta.validation.constraints.NotNull;

public record DeleteMemberRequest(
    String reason
) {
}
