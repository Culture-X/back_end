package TripAmi.backend.web.api.auth.requset;

import javax.validation.constraints.Email;

public record updateTempPasswordRequest(
    @Email String email
) {
}
