package TripAmi.backend.app.util;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Address {
    @Column(nullable = false)
    String address;
    String detail;

    void setAddress(String address, String detail) {
        this.address = address;
        this.detail = detail;
    }
}
