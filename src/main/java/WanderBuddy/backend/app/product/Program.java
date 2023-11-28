package WanderBuddy.backend.app.product;

import WanderBuddy.backend.app.util.infra.StringListConverter;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@DiscriminatorValue("Program")
@PrimaryKeyJoinColumn(name = "Program_Id")
public class Program extends Product {
    Long amiId;
    Integer totalPersonnel;
    LocalDateTime startDateTime;
    LocalDateTime finishDateTime;
    @Enumerated(value = EnumType.STRING)
    ProgramTheme theme;
    @Convert(converter = StringListConverter.class)
    List<String> keyWords;
    //todo address class 작성
    String location;
}
