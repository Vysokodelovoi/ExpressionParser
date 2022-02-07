package expression.exceptions;

import expression.*;
import info.kgeorgiy.json.BaseParser;
import info.kgeorgiy.json.CharSource;
import info.kgeorgiy.json.StringSource;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class ExpressionParser extends BaseParser implements Parser {
    private final Set<Character> binaryOperatorSymbols = Set.of('+', '-', '*', '/',  'm', 'i',
        'n', 'a', 'x');
    private final Set<Character> unaryOperatorSymbols = Set.of('t','0', 'l');
    private final Set<String> unaryOperators = Set.of("l0", "t0");
    private final Set<String> binaryOperators = Set.of("+", "-", "*", "/", "min", "max");
    private final Map<Character, Integer> priorityByChar = Map.of(
            '+', 1,
            '-', 1,
            '/', 2,
            '*', 2,
            'm', 0
    );
    public ExpressionParser() {
        super();
    }

    protected void initialize(CharSource source) {
        this.source = source;
        take();
    }

    public MyExpression parse(String s) {
        initialize(new StringSource(s));
        return parseExpression();
    }

    private MyExpression parseExpression() {
        MyExpression first;
        first = parseTerm(0);
        return  first;
    }

    private MyExpression parseTerm(int priority) {
        MyExpression firstTerm, secondTerm;
        skipWhitespace();
        if (take('(')) {
            firstTerm = parseExpression();
            take(')');
        } else if (take('-')) {
            if (between('0', '9')) {
                firstTerm = parseConst(true);
            } else {
                firstTerm = new CheckedNegate(parseTerm(3));
            }
        } else if (ch == 'l' || ch == 't') {
          firstTerm = makeUnaryExpression(parseUnaryOperator(), parseTerm(3));
        } else if (between('a', 'z')) {
            firstTerm = parseVariable();
        } else {
            firstTerm = parseConst(false);
        }
        skipWhitespace();
        while (isPriority(priority)) {
            String op = parseBinaryOperator();
            secondTerm = parseTerm(priorityByChar.get(op.charAt(0)) + 1);
            firstTerm = makeBinaryExpression(firstTerm, secondTerm, op);
        }
        return firstTerm;
    }

    private MyExpression makeBinaryExpression(MyExpression firstExpression, MyExpression secondExpression, String curOperation) {
        return switch (curOperation) {
            case "+" -> new CheckedAdd(firstExpression, secondExpression);
            case "-" -> new CheckedSubtract(firstExpression, secondExpression);
            case "*" -> new CheckedMultiply(firstExpression, secondExpression);
            case "/" -> new CheckedDivide(firstExpression, secondExpression);
            case "max" -> new Maximum(firstExpression, secondExpression);
            case "min" -> new Minimum(firstExpression, secondExpression);
            default -> throw new NoSuchElementException("Cannot resolve operation " + curOperation);
        };
    }

    private UnaryExpression makeUnaryExpression(String op, MyExpression subExpression) {
        return switch (op) {
            case "l0" -> new Nolz(subExpression);
            case "t0" -> new Notz(subExpression);
            default -> throw new NoSuchElementException("Cannot resolve unary operation " + op);
        };
    }

    private String parseBinaryOperator() {
        return parseFromSet(binaryOperatorSymbols, binaryOperators);
    }

    private  String parseUnaryOperator() {
        String op = parseFromSet(unaryOperatorSymbols, unaryOperators);
        if (!unaryOperators.contains(op)) {
            throw new NoSuchElementException("There is no such binary operation with name " + op);
        }
        return op;
    }

    private String parseFromSet(Set<Character> possibleSymbols, Set<String> possibleTokens) {
        StringBuilder op = new StringBuilder();
        while (possibleSymbols.contains(ch)) {
            op.append(take());
            if (possibleTokens.contains(op.toString())) {
                return op.toString();
            }
        }
        return  op.toString();
    }

    private Const parseConst(boolean isNegative) {
        StringBuilder constant = new StringBuilder();
        if (isNegative) {
            constant.append('-');
        }
        while (between('0', '9') ) {
            constant.append(take());
        }
        return new Const(Integer.parseInt(constant.toString()));
    }

    private Variable parseVariable() {
        if (between('x', 'z')) {
            return new Variable(String.valueOf(take()));
        }
        return null;
    }

    private boolean isPriority(int priority) {
        return priorityByChar.getOrDefault(ch, -1) >= priority;
    }
}
