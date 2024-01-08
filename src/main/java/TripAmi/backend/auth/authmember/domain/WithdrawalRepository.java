package TripAmi.backend.auth.authmember.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WithdrawalRepository extends JpaRepository<Withdrawal, Long> {
    Optional<Withdrawal> findByAuthMember(AuthMember authMember);
}
