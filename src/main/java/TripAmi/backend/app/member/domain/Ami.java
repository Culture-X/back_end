package TripAmi.backend.app.member.domain;

import TripAmi.backend.app.product.domain.Program;
import TripAmi.backend.app.util.Star;
import TripAmi.backend.auth.authmember.domain.AuthMember;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "ami")
public class Ami {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ami_id")
    private Long id;
    @Embedded
    private Rating rating = new Rating();
    @OneToOne(mappedBy = "ami")
    private AuthMember authMember;
    @OneToMany(mappedBy = "ami", cascade = CascadeType.ALL)
    private List<Program> programs = new ArrayList<>();

    public void setAuthMember(AuthMember authMember) {
        this.authMember = authMember;
    }

    public void addProgram(Program program) {
        programs.add(program);
    }

    /**
     * 별점 별 개수를 업데이트
     *
     * @param stars 별점
     */
    public void updateRating(List<Star> stars) {
        for (Star star : stars) {
            this.rating.addStar(star);
        }
    }

    public void updateRating(Star star) {
        this.rating.addStar(star);
    }

    public BigDecimal getRating() {
        return this.rating.getRating();
    }
}
