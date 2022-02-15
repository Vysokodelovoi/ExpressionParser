package expression.exceptions;

public class UnclosedParenthesisException extends ParseExceptions {

    public UnclosedParenthesisException() {
        super("')' expected, found end of file");
    }
}
