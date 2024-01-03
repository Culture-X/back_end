package TripAmi.backend.auth.authmember.service.event;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GeneratedCodeEvent {
    String email;
    String mailForm;
    String code;

    @Builder
    public GeneratedCodeEvent(String email, String mailForm, String code) {
        this.email = email;
        this.mailForm = mailForm;
        this.code = code;
    }
}
