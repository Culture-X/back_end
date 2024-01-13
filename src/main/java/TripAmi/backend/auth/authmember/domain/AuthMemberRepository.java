package TripAmi.backend.auth.authmember.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthMemberRepository extends JpaRepository<AuthMember, Long> {
    Optional<AuthMember> findByEmail(String email);
    Boolean existsAuthMemberByEmail(String email);
    Boolean existsAuthMemberByNickname(String nickname);
}