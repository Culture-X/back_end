package TripAmi.backend.app.product.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProgramRepository extends JpaRepository<Program, Long> {

    @Query("SELECT p FROM Program p WHERE p.title = :title")
    List<Program> findByTitle(String title);
}
