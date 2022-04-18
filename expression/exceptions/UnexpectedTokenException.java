package expression.exceptions;

public class UnexpectedTokenException extends ParseExceptions {
    public UnexpectedTokenException(String message, int position) {
        super("Operator or end of expression expected, found:\t"+message, position);
    }
}
