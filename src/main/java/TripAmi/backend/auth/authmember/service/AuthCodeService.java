package TripAmi.backend.auth.authmember.service;

import java.time.LocalDateTime;

public interface AuthCodeService {
    String save(String email, LocalDateTime inputTime);

    String update(String email, LocalDateTime inputTime);

    String getAuthCode(String email, LocalDateTime inputTime);

    void confirm(String email, String inputCode, LocalDateTime inputTime);

    void verifyConfirmedEmail(String email);
}
