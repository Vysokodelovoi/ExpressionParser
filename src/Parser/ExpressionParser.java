package Parser;
import info.kgeorgiy.json.BaseParser;
import expression.*;
import info.kgeorgiy.json.CharSource;
import info.kgeorgiy.json.StringSource;

import java.util.NoSuchElementException;
import java.util.Set;

public class ExpressionParser extends BaseParser {
    private Set<Character> operators = Set.of('+', '-', '*', '/');

    protected ExpressionParser() {
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
        MyExpression first, second;
        first = parseTerm();
        skipWhitespace();
        String op = parseOperator();
        skipWhitespace();
        while (op.length() != 0) {
            second = parseTerm();
            first = makeExpression(first, second, op);
            skipWhitespace();
            op = parseOperator();
            skipWhitespace();
        }
        return  first;
    }

    private MyExpression parseTerm() {
        MyExpression firstTerm, secondTerm;
        skipWhitespace();
        if (take('(')) {
            firstTerm = parseExpression();
            take(')');
        } else if (take('-')) {
            firstTerm = new UnaryMinus(parseTerm());
        } else if (between('a', 'z')) {
            firstTerm = parseVariable();
        } else {
            firstTerm = parseConst();
        }
        skipWhitespace();
        if (isHighPriority()) {
            String op = parseOperator();
            secondTerm = parseTerm();
            return makeExpression(firstTerm, secondTerm, op);
        }
        return firstTerm;
    }

    private MyExpression makeExpression(MyExpression firstExpression, MyExpression secondExpression, String curOperation) {
        return switch (curOperation) {
            case "+" -> new Add(firstExpression, secondExpression);
            case "-" -> new Subtract(firstExpression, secondExpression);
            case "*" -> new Multiply(firstExpression, secondExpression);
            case "/" -> new Divide(firstExpression, secondExpression);
            default -> throw new NoSuchElementException("Cannot resolve operation " + curOperation);
        };
    }

    private String parseOperator() {
        StringBuilder op = new StringBuilder();
        while (isOperator()) {
            op.append(take());
        }
        return op.toString();
    }
    private Const parseConst() {
        StringBuilder constant = new StringBuilder();
        if (take('-')) {
            constant.append('-');
        }
        if (take('0')) {
            constant.append('0');
        } else if (between('1', '9')) {
            constant.append(take());
        } else {
            return null;
        }
        return new Const(Integer.parseInt(constant.toString()));
    }
    private Variable parseVariable() {
        if (between('a', 'z')) {
            return new Variable(String.valueOf(take()));
        }
        return null;
    }

    private boolean isOperator() {
        return operators.contains(ch);
    }
    private boolean isHighPriority() {
        return ch == '*' || ch == '/';
    }
}
