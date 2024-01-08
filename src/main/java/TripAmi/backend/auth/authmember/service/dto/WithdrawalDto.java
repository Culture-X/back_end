package TripAmi.backend.auth.authmember.service.dto;

import lombok.Builder;

public record WithdrawalDto(Long id, Long memberId, String reason) {
    @Builder
    public WithdrawalDto(Long id, Long memberId, String reason) {
        this.id = id;
        this.memberId = memberId;
        this.reason = reason;
    }
}
