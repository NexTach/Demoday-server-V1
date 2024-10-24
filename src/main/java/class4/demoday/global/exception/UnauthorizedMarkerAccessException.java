package class4.demoday.global.exception;

public class UnauthorizedMarkerAccessException extends RuntimeException {
    public UnauthorizedMarkerAccessException(String message) {
        super(message);
    }
}