package TripAmi.backend.app.product;

import TripAmi.backend.app.util.EntityBase;
import TripAmi.backend.app.util.infra.StringListConverter;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE")
public abstract class Product extends EntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    @Convert(converter = StringListConverter.class)
    List<String> images = new ArrayList<>();
    String content;
    Integer price;
}
