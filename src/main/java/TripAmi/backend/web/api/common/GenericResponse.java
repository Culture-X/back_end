package TripAmi.backend.web.api.common;

import TripAmi.backend.web.api.program.response.ThemeListResponse;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class GenericResponse<T> {
    private final int statusCode;
    private final T data;
    private final String message;
    @Builder
    public GenericResponse(int statusCode, T data, String message) {
        this.statusCode = statusCode;
        this.data = data;
        this.message = message;
    }

    public static <T> GenericResponse<T> ok(T data) {
        return new GenericResponse<>(HttpStatus.OK.value(), data, HttpStatus.OK.getReasonPhrase());
    }

    public static <T> GenericResponse<T> ok() {
        return new GenericResponse<>(HttpStatus.OK.value(), null, HttpStatus.OK.getReasonPhrase());
    }
}
