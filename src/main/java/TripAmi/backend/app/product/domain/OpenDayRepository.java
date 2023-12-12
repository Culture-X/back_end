package TripAmi.backend.app.product.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OpenDayRepository extends JpaRepository<OpenDay, Long> {

    @Query("SELECT o FROM OpenDay o WHERE o.program.id = : programId")
    List<OpenDay> findByProgramId(Long programId);
}
