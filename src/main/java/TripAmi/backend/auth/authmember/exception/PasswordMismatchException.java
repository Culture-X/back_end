package TripAmi.backend.auth.authmember.exception;

public class PasswordMismatchException extends RuntimeException {
    public PasswordMismatchException() {
    }

    public PasswordMismatchException(String message) {
        super(message);
    }
}
