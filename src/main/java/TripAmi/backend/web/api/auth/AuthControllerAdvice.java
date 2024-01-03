//package TripAmi.backend.web.api.auth;
//
//import TripAmi.backend.auth.authmember.service.exception.EmailNotFoundException;
//import TripAmi.backend.auth.authmember.service.exception.ConfirmNotFoundException;
//import TripAmi.backend.web.api.common.ErrorResponse;
//import TripAmi.backend.web.api.common.StatusCode;
//import  javax.servlet.http.HttpServletRequest;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//@RestControllerAdvice
//public class AuthControllerAdvice {
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(EmailNotFoundException.class)
//    public ErrorResponse<String> handleMemberNotFoundException(EmailNotFoundException e, HttpServletRequest request) {
//        return new ErrorResponse(StatusCode.NOT_FOUND, e.getMessage(), request.getRequestURI()) {
//        };
//    }
//
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(ConfirmNotFoundException.class)
//    public ErrorResponse<String> handleConfirmNotFoundException(ConfirmNotFoundException e, HttpServletRequest request) {
//        return new ErrorResponse<>(StatusCode.NOT_FOUND, e.getMessage(), request.getRequestURI());
//    }
//
//}
