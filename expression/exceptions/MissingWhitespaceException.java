package expression.exceptions;

public class MissingWhitespaceException extends ParseExceptions {
    public MissingWhitespaceException(String message, int position) {
        super("Operator " + message + " requires whitespace", position);
    }
}
