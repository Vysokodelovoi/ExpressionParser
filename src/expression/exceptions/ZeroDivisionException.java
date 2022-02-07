package expression.exceptions;

public class ZeroDivisionException extends EvaluateException {
    public ZeroDivisionException(String message) {
        super(message + " equals to zero on giving arguments");
    }
}
