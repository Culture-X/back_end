package WanderBuddy.backend.app.product;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

import java.sql.Time;

@Entity
@DiscriminatorValue("Plan")
@PrimaryKeyJoinColumn(name = "Plan_id")
public class Plan extends Product {
    Boolean soldOut;
    Integer recommendedPersonnel;
    //todo converter 작성, set으로 수정
    AgeGroup recommendedAgeGroups;
    Time requiredTime;
    Integer cost;
}
