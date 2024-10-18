package class4.demoday.global.exception;

public class IdandPasswordNotMatchException extends RuntimeException {
    public IdandPasswordNotMatchException(String message) {
        super(message);
    }
}