package TripAmi.backend.web.api.common;

import lombok.Builder;
import lombok.Getter;

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
}
