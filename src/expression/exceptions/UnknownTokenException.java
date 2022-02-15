package expression.exceptions;

public class UnknownTokenException extends ParseExceptions {
    public UnknownTokenException(String message) {
        super("Unknown token:\t"+message);
    }
}
