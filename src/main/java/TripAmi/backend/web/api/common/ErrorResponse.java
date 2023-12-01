package TripAmi.backend.web.api.common;

import lombok.Getter;

@Getter
public class ErrorResponse<T> {
    private final int statusCode;
    private final T message;
    private final String path;

    public ErrorResponse(int statusCode, T message, String path) {
        this.statusCode = statusCode;
        this.message = message;
        this.path = path;
    }
}
