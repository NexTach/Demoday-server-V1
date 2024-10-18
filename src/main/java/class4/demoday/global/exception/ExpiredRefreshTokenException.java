package class4.demoday.global.exception;

public class ExpiredRefreshTokenException extends RuntimeException {
    public ExpiredRefreshTokenException(String message) {
        super(message);
    }
}