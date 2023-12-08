package TripAmi.backend.app.member.domain;

import TripAmi.backend.app.util.Star;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;

@Embeddable
@Getter
public class Rating {
    @Column(name = "one_star_nb")
    private Integer oneStarNb;
    @Column(name = "two_star_nb")
    private Integer twoStarNb;
    @Column(name = "three_star_nb")
    private Integer threeStarNb;
    @Column(name = "four_star_nb")
    private Integer fourStarNb;
    @Column(name = "five_star_nb")
    private Integer fiveStarNb;

    public void addStar(Star star) {
        switch (star) {
            case ONE:
                oneStarNb++;
                break;
            case TWO:
                twoStarNb++;
                break;
            case THREE:
                threeStarNb++;
                break;
            case FOUR:
                fourStarNb++;
                break;
            case FIVE:
                fiveStarNb++;
                break;
        }
    }

    public BigDecimal getRating() {
        return valueOf(getCumulativeSum())
                   .divide(valueOf(getTotalNum()), HALF_UP)
                   .setScale(5, HALF_UP);
    }

    private Integer getCumulativeSum() {
        return oneStarNb + 2 * twoStarNb + 3 * threeStarNb + 4 * fourStarNb + 5 * fiveStarNb;
    }

    private Integer getTotalNum() {
        return (oneStarNb + twoStarNb + threeStarNb + fourStarNb + fiveStarNb);
    }
}
