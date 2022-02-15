package expression.exceptions;

import expression.*;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import static expression.exceptions.TokenParser.*;
import static expression.exceptions.TokenType.*;

public class ExpressionParser implements Parser {
    private final Map<String, Integer> priorityByOperator = Map.of(
            "+", 1,
            "-", 1,
            "min", 0,
            "max", 0,
            "/", 2,
            "*", 2,
            "//", 3,
            "**", 3
    );
    private final Set<String> whitespacesOperations = Set.of("min", "max");
    private TokenParser tokenParser;

    public ExpressionParser() {
        super();
    }

    protected void initialize(String source) {
        tokenParser = new TokenParser(source);
    }

    public MyExpression parse(String s) {
        initialize(s);
        return parseExpression();
    }

    private MyExpression parseExpression() {
        MyExpression first;
        first = parseTerm(0);
        if (!tokenParser.eof()) {
            throw new UnexpectedTokenException(tokenParser.nextToken().getSelf());
        }
        if (tokenParser.getBalance() > 0) {
            throw new UnclosedParenthesisException();
        }
        if (tokenParser.getBalance() < 0) {
            throw new UnexpectedTokenException(tokenParser.getCurToken().getSelf());

        }
        return first;
    }

    private MyExpression parseTerm(int priority) {
        MyExpression firstTerm, secondTerm;
        Token curToken = tokenParser.nextNotWhitespace();
        switch (curToken.getType()) {
            case LEFT_PARANTH -> {
                firstTerm = parseTerm(0);
                tokenParser.nextToken();
            }
            case VARIABLE -> firstTerm = new Variable(curToken.getSelf());
            case CONSTANT -> {
                try {
                    firstTerm = new Const(Integer.parseInt(curToken.getSelf()));
                } catch (NumberFormatException exception) {
                    throw new OverFlowException("constant " + curToken.getSelf(), curToken.getSelf() + "is too long");
                }
            }
            case UNARY -> {
                if (!curToken.getSelf().equals("-") && !(Character.isWhitespace(tokenParser.getCh()) ||
                        tokenParser.getCh() == '-' || tokenParser.getCh() == '(')) {
                    throw new MissingWhitespaceException(curToken.getSelf());
                }
                firstTerm = makeUnary(curToken.getSelf(), parseTerm(4));
            }
            default -> throw new MissingOperandException(curToken.getSelf());
        }
        if (tokenParser.getCurToken().getType() != OPERATOR &&
                tokenParser.getCurToken().getType() != RIGHT_PARANTH &&
                tokenParser.nextToken().getType() == WHITESPACE) {
            tokenParser.nextToken();
        }
        while (tokenParser.getCurToken().getType() != RIGHT_PARANTH &&
                isPriority(tokenParser.getCurToken(), priority)) {
            String op = tokenParser.getCurToken().getSelf();
            if (whitespacesOperations.contains(op) && !(tokenParser.getWereWhitespace() &&
                    (Character.isWhitespace(tokenParser.getCh()) ||
                            tokenParser.getCh() == '-') ||
                    tokenParser.getCh() == '(')) {
                throw new MissingWhitespaceException(op);
            }
            secondTerm = parseTerm(priorityByOperator.get(op) + 1);
            firstTerm = makeBinary(firstTerm, secondTerm, op);
        }
        return firstTerm;
    }

    private boolean isPriority(Token curToken, int priority) {
        if (curToken.getType() != OPERATOR) {
            if (curToken.getType() != END && curToken.getType() != RIGHT_PARANTH) {
                throw new UnexpectedTokenException("operator of end of file expected, found " + curToken.getSelf());
            }
            return false;
        }
        return priorityByOperator.get(curToken.getSelf()) >= priority;
    }

    private MyExpression makeBinary(MyExpression firstExpression, MyExpression secondExpression, String curOperation) {

        return switch (curOperation) {
            case "+" -> new CheckedAdd(firstExpression, secondExpression);
            case "-" -> new CheckedSubtract(firstExpression, secondExpression);
            case "*" -> new CheckedMultiply(firstExpression, secondExpression);
            case "/" -> new CheckedDivide(firstExpression, secondExpression);
            case "max" -> new Maximum(firstExpression, secondExpression);
            case "min" -> new Minimum(firstExpression, secondExpression);
            case "**" -> new CheckedPow(firstExpression, secondExpression);
            case "//" -> new CheckedLog(firstExpression, secondExpression);
            default -> throw new NoSuchElementException("Cannot resolve operation " + curOperation);
        };
    }

    private UnaryExpression makeUnary(String op, MyExpression subExpression) {
        return switch (op) {
            case "l0" -> new Nolz(subExpression);
            case "-" -> new CheckedNegate(subExpression);
            case "t0" -> new Notz(subExpression);
            case "abs" -> new Abs(subExpression);
            default -> throw new NoSuchElementException("Cannot resolve unary operation " + op);
        };
    }
}
