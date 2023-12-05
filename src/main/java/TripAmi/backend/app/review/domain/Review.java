package TripAmi.backend.app.review.domain;

import TripAmi.backend.app.order.domain.Order;
import TripAmi.backend.app.util.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "review")
@Getter
public class Review {
    @Id
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Order order;

    private Integer rating;

    private String content;

    @Embedded
    private BaseEntity baseEntity;

    @Builder
    public Review(Order order, Integer rating, String content) {
        this.order = order;
        this.rating = rating;
        this.content = content;
    }
}
