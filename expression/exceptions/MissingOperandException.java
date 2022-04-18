package expression.exceptions;

public class MissingOperandException extends ParseExceptions {
    public MissingOperandException(String message, int position) {
        super("Operand expected, found: '" + message + "'", position);
    }
}
