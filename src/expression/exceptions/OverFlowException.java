package expression.exceptions;

public class OverFlowException extends EvaluateException {
    public OverFlowException(String expression, String message) {
        super("Overflow occurred at " + expression + ":\t" +message);
    }
}
