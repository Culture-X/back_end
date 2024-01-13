package TripAmi.backend.auth.authmember.service;

import TripAmi.backend.auth.authmember.domain.AuthMember;
import TripAmi.backend.auth.authmember.service.dto.WithdrawalDto;

import java.util.List;

public interface WithdrawalService {
    void save(AuthMember authMember, String reason);
    List<WithdrawalDto> findWithdrawals();

}
