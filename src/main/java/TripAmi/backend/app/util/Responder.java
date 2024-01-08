package TripAmi.backend.app.util;

import TripAmi.backend.auth.authmember.domain.AuthMember;
import TripAmi.backend.auth.exception.ExceptionCode;

import javax.servlet.http.HttpServletResponse;

public class Responder {
    public static void sendErrorResponse(HttpServletResponse response, ExceptionCode exceptionCode) {
    }

    public static void loginSuccessResponse(HttpServletResponse response, AuthMember authMember) {

    }
}
