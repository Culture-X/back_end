package TripAmi.backend.app.order.domain;

import TripAmi.backend.app.util.BaseEntity;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

//    private Product product;

    private Integer price;

    private Integer count;

    @Embedded
    private BaseEntity baseEntity = new BaseEntity();

    @Builder
    public Order( Integer price, Integer count) {
//        this.product = product;
        this.price = price;
        this.count = count;
    }
}

