package TripAmi.backend.app.member.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AmiRepository extends JpaRepository<Ami, Long> {
    Optional<Ami> findAmiById(Long id);
}
