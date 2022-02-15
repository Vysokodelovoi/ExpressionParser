package expression.exceptions;

public class NoExpectedOperandException extends ParseExceptions {
    public NoExpectedOperandException(String message, int position) {
        super("Operand expected, found " + message + "\tat position " + position);
    }
}
