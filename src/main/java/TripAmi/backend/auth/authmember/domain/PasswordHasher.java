package TripAmi.backend.auth.authmember.domain;

public interface PasswordHasher {

    String hash(String rawPassword);
    boolean isMatch(String raw, String hashed);
}
