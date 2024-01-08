package TripAmi.backend.app.util;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Getter
public class BaseEntity {
    @CreatedDate
    @Column(name = "created_at")
    LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    LocalDateTime updatedAt;
    Boolean deleted;

    public void delete() {
        this.deleted = true;
    }

    public BaseEntity() {
        this.deleted = false;
    }
}
