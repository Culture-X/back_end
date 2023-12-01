package TripAmi.backend.app.tourist.domain;

import TripAmi.backend.app.member.domain.Member;
//import WanderBuddy.backend.app.plan.domain.Plan;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tourist extends Member {

    private String profileImgUrl;



    @Builder
    public Tourist(String profileImgUrl, UUID uuid) {
        this.profileImgUrl = profileImgUrl;
        updateUserUuid(uuid);
    }

    private void updateUserUuid(UUID uuid) {
        super.updateUuid(uuid);
    }
}
