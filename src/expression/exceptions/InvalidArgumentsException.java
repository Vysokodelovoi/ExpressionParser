package expression.exceptions;

public class InvalidArgumentsException extends EvaluateException {
    public InvalidArgumentsException(String message) {
        super("Invalid arguments for function " +  message);
    }
}
