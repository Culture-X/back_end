package TripAmi.backend.auth.authmember.domain;

import TripAmi.backend.app.member.domain.Ami;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface AuthMemberRepository extends JpaRepository<AuthMember, Long> {
    Optional<AuthMember> findByEmail(String email);
    Boolean existsAuthMemberByEmail(String email);
    Boolean existsAuthMemberByNickname(String nickname);
}