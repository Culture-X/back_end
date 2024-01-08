package TripAmi.backend.auth.authmember.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthCodeRepository extends JpaRepository<AuthCode, Long> {
    Optional<AuthCode> findAuthCodeByEmail(String email);
}
