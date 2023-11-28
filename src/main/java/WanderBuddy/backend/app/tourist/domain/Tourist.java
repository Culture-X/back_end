package WanderBuddy.backend.app.tourist.domain;

import WanderBuddy.backend.app.member.domain.Member;
//import WanderBuddy.backend.app.plan.domain.Plan;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import java.util.Set;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tourist extends Member {

    private String profileImgUrl;

//    private Set<Plan> planWish;

//    private Set<Program> programWish;

    @Builder
    public Tourist(String profileImgUrl) {
        this.profileImgUrl = profileImgUrl;
    }
}
