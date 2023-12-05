package TripAmi.backend.app.order.domain;

import TripAmi.backend.app.product.Product;
import TripAmi.backend.app.util.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "order")
public class Order {
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    private Product product;

    private Integer price;

    private Integer count;

    @Embedded
    private BaseEntity baseEntity;

    @Builder
    public Order( Integer price, Integer count, BaseEntity baseEntity) {
//        this.product = product;
        this.price = price;
        this.count = count;
        this.baseEntity = baseEntity;
    }
}

