package class4.demoday.global.exception;

public class JsonFormatException extends RuntimeException {
    public JsonFormatException(String message) {
        super(message);
    }
}