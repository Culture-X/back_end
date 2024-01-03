package TripAmi.backend.web.api.member.request;

import TripAmi.backend.auth.authmember.domain.Role;

import javax.validation.constraints.NotNull;

public record SelectRoleRequest(
    @NotNull
    String email,
    @NotNull
    Role role
) {
}
