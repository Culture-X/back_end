package TripAmi.backend.app.util;

import jakarta.persistence.Embeddable;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Embeddable
@Getter
public class BaseEntity {
    @CreatedDate
    LocalDateTime createdAt;
    @LastModifiedDate
    LocalDateTime updatedAt;
    Boolean deleted;
    public void delete() {
        this.deleted = true;
    }

    public BaseEntity() {
        this.deleted = false;
    }
}
