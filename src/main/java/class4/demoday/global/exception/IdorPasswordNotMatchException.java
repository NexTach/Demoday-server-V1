package class4.demoday.global.exception;

public class IdorPasswordNotMatchException extends RuntimeException {
    public IdorPasswordNotMatchException(String message) {
        super(message);
    }
}