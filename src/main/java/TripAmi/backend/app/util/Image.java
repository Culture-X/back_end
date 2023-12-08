package TripAmi.backend.app.util;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public abstract class Image {
    private String url;
}
