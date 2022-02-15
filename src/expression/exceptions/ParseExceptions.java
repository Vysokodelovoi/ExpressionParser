package expression.exceptions;

public class ParseExceptions extends RuntimeException {
    public ParseExceptions(String message) {
        super("Invalid expression: " + message);
    }
}
