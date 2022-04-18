package expression.exceptions;

public class UnknownTokenException extends ParseExceptions {
    public UnknownTokenException(String message, int position) {
        super("Unknown token:\t"+message, position);
    }
}
