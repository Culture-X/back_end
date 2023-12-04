package TripAmi.backend.app.ami.domain;

import TripAmi.backend.app.Language;
import TripAmi.backend.app.member.domain.Member;
//import WanderBuddy.backend.app.plan.domain.Plan;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ami extends Member {
    private String profileImgUrl;

//    private Set<Plan> planWish;

    private Set<Language> languages;

    private BigDecimal rating;

    private String introduce;

    private String strength;

//    private Set<Program> wishPlan;
}
