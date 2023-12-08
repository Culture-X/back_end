package TripAmi.backend.app.product;

import TripAmi.backend.app.util.infra.StringListConverter;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("Program")
@PrimaryKeyJoinColumn(name = "Program_Id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Program extends Product {
    Long amiId;

    @OneToMany(mappedBy = "program")
    List<Spot> spots = new ArrayList<>();



    Integer totalPersonnel;
    @Enumerated(value = EnumType.STRING)
    ProgramTheme theme;
    @Convert(converter = StringListConverter.class)
    List<String> keyWords;
    //todo address class 작성
    String location;

    @Builder
    public Program(String title, List<String> images, String content, Integer price, List<Spot> spots, Long amiId, Integer totalPersonnel, ProgramTheme theme, List<String> keyWords, String location) {
        super(title, images, content, price);
        this.amiId = amiId;
        this.spots.addAll(spots);
        this.totalPersonnel = totalPersonnel;
        this.theme = theme;
        this.keyWords = keyWords;
        this.location = location;
    }
}
