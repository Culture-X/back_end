package TripAmi.backend.app.reservation.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT r FROM Reservation r WHERE r.program.id = :programId")
    List<Reservation> findByProgramId(Long programId);

    Optional<Reservation> findById(Long id);
}
