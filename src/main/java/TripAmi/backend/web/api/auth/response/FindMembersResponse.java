package TripAmi.backend.web.api.auth.response;

import TripAmi.backend.app.member.service.dto.MemberDto;
import lombok.Builder;

import java.util.List;

public record FindMembersResponse(
    List<MemberDto> memberDtos
) {
    @Builder
    public FindMembersResponse {
    }
}
