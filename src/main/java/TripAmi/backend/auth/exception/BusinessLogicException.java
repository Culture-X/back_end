package TripAmi.backend.auth.exception;

public class BusinessLogicException extends RuntimeException{
    public BusinessLogicException(ExceptionCode exceptionCode) {
        super(exceptionCode.name());
    }
}
