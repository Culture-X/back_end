package TripAmi.backend.app.util;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public abstract class BaseEntity {
    @CreatedDate
    LocalDateTime createdAt;
    @LastModifiedDate
    LocalDateTime updatedAt;
    Boolean deleted;

    public void deleteData() {
        this.deleted = true;
    }
}
