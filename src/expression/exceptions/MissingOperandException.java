package expression.exceptions;

public class MissingOperandException extends ParseExceptions {
    public MissingOperandException(String message) {
        super("Operand expected, found: '" + message + "'");
    }
}
