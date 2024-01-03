package TripAmi.backend.web.api.auth.requset;

import javax.validation.constraints.NotNull;

public record UpdatePasswordRequest(
    @NotNull String password
) {
}
