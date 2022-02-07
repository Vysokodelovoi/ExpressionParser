package expression.exceptions;

public class OverFlowException extends EvaluateException {
    public OverFlowException(String expression, String message) {
        super("Overflowed occurred at " + expression + ":\n" +message);
    }
}
