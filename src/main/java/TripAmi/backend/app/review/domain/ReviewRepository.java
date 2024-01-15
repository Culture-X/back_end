package TripAmi.backend.app.review.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findById(Long id);
    @Query("select r from Review r where r.reservation.program.id = :programId")
    List<Review> findAllByProgramId(Long programId);
}
