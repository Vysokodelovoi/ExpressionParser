package expression.exceptions;

public class MissingWhitespaceException extends ParseExceptions {
    public MissingWhitespaceException(String message) {
        super("Operator " + message + " requires whitespace");
    }
}
