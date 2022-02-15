package expression.exceptions;

public class NoSpaceSymbolsException extends ParseExceptions {
    public NoSpaceSymbolsException(String message) {
        super("Expected spaces with operation " + message);
    }
}
