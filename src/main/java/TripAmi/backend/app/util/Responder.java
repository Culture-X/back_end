package TripAmi.backend.app.util;

import TripAmi.backend.auth.authmember.domain.AuthMember;
import TripAmi.backend.auth.exception.ExceptionCode;
import TripAmi.backend.web.api.auth.response.LoginResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Responder {
    public static void sendErrorResponse(HttpServletResponse response, ExceptionCode exceptionCode) {
    }

    public static void loginSuccessResponse(HttpServletResponse response, AuthMember authMember) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        PrintWriter writer = response.getWriter();
        writer.write(objectMapper.writeValueAsString(LoginResponse.builder()
                                                         .email(authMember.getEmail())
                                                         .nickname(authMember.getNickname())
                                                         .imgUrl(authMember.getImgUrl())
                                                         .build()));
        writer.close();
    }
}
