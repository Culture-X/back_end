package TripAmi.backend.app.product.domain;

import TripAmi.backend.app.product.ProgramTheme;
import TripAmi.backend.app.util.infra.StringListConverter;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("program")
@PrimaryKeyJoinColumn(name = "program_id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Program extends Product {
    @Column(name = "ami_id")
    Long amiId;

    @OneToMany(mappedBy = "program")
    List<Spot> spots = new ArrayList<>();

    @Column(name = "total_people")
    Integer totalPeople;

    @Enumerated(value = EnumType.STRING)
    ProgramTheme theme;

    @Convert(converter = StringListConverter.class)
    List<String> keywords;

    //todo address class 작성
    String location;

    @Builder
    public Program(String title, List<String> images, String content, Integer price, List<Spot> spots, Long amiId, Integer totalPeople, ProgramTheme theme, List<String> keywords, String location) {
        super(title, images, content, price);
        this.amiId = amiId;
        this.spots.addAll(spots);
        this.totalPeople = totalPeople;
        this.theme = theme;
        this.keywords = keywords;
        this.location = location;
    }
}
