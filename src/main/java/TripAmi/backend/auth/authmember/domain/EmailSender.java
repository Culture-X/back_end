package TripAmi.backend.auth.authmember.domain;

public interface EmailSender {
    void sendEmail(String from, String to, String Content);
}
