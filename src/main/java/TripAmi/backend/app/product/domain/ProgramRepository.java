package TripAmi.backend.app.product.domain;

import TripAmi.backend.app.product.ProgramTheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProgramRepository extends JpaRepository<Program, Long> {

    @Query("SELECT p FROM Program p WHERE p.title = :title")
    List<Program> findByTitle(String title);

    @Query("SELECT p FROM Program p WHERE p.theme = :theme")
    List<Program> findByTheme(@Param("theme") ProgramTheme theme);
}
