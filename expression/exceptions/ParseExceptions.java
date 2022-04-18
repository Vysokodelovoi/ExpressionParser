package expression.exceptions;

public class ParseExceptions extends Exception {
    public ParseExceptions(String message, int position) {
        super("Invalid expression: " + message + ". At position " + position);
    }
}
