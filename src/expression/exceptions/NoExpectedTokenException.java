package expression.exceptions;

public class NoExpectedTokenException extends ParseExceptions {
    public NoExpectedTokenException(String message, int position) {
        super("No operand for operation " + message + "\nat position " + position);
    }
}
