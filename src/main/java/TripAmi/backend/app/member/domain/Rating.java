package TripAmi.backend.app.member.domain;

import TripAmi.backend.app.util.Star;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Builder
    public Rating() {
        this.oneStarNb = 0;
        this.twoStarNb = 0;
        this.threeStarNb = 0;
        this.fourStarNb = 0;
        this.fiveStarNb = 0;
    }

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
