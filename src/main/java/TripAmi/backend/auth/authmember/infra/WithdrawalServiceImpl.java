package TripAmi.backend.auth.authmember.infra;

import TripAmi.backend.auth.authmember.domain.AuthMember;
import TripAmi.backend.auth.authmember.domain.Withdrawal;
import TripAmi.backend.auth.authmember.domain.WithdrawalRepository;
import TripAmi.backend.auth.authmember.service.WithdrawalService;
import TripAmi.backend.auth.authmember.service.dto.WithdrawalDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WithdrawalServiceImpl implements WithdrawalService {
    private final WithdrawalRepository withdrawalRepository;

    @Override
    @Transactional
    public void save(AuthMember authMember, String reason) {
        withdrawalRepository.save(
            Withdrawal.builder()
                .authMember(authMember)
                .reason(reason)
                .build()
        );
    }

    @Override
    public List<WithdrawalDto> findWithdrawals() {
        List<WithdrawalDto> withdrawalDtos = new ArrayList<>();
        List<Withdrawal> withdrawals = withdrawalRepository.findAll();
        for (Withdrawal withdrawal : withdrawals) {
            withdrawalDtos.add(WithdrawalDto.builder()
                                   .id(withdrawal.getId())
                                   .memberId(withdrawal.getAuthMember().getId())
                                   .reason(withdrawal.getReason())
                                   .build());
        }
        return withdrawalDtos;
    }
}
