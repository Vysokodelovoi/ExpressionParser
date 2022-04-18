package expression.exceptions;

public class UnclosedParenthesisException extends ParseExceptions {
    public UnclosedParenthesisException(int position) {
        super("')' expected, found end of file", position);
    }
}
