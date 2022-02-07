package expression.exceptions;

public class UnexpectedTokenException extends ParseExceptions {

    public UnexpectedTokenException(String message) {
        super("Operator or end of expression expected, found:\t"+message);
    }
}
