package TripAmi.backend.app.product.domain;

import TripAmi.backend.app.util.BaseEntity;
import TripAmi.backend.app.util.infra.StringListConverter;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "product")
@Getter
public abstract class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title;

    @Convert(converter = StringListConverter.class)
    List<String> images = new ArrayList<>();

    String content;

    Integer price;

    public Product(String title, List<String> images, String content, Integer price) {
        this.title = title;
        this.images = images;
        this.content = content;
        this.price = price;
    }
}
