package TripAmi.backend.web.api.member.request;

import TripAmi.backend.auth.authmember.domain.Role;

import java.util.Set;

public record LoginRequest(String email, String password) {
}
