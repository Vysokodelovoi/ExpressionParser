package expression.parser;
import info.kgeorgiy.json.BaseParser;
import expression.*;
import info.kgeorgiy.json.CharSource;
import info.kgeorgiy.json.StringSource;

import java.util.NoSuchElementException;
import java.util.Set;

public class ExpressionParser extends BaseParser implements Parser {
    private Set<Character> operatorSymbols = Set.of('+', '-', '*', '/');
    private Set<String> operators = Set.of("+", "-", "*", "/");

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
        MyExpression first, second;
        first = parseAddTerm();
        skipWhitespace();
        String op = parseOperator();
        skipWhitespace();
        while (op.length() != 0) {
            second = parseAddTerm();
            first = makeExpression(first, second, op);
            skipWhitespace();
            op = parseOperator();
            skipWhitespace();
        }
        return  first;
    }

    private MyExpression parseAddTerm() {
        MyExpression firstTerm, secondTerm;
        skipWhitespace();
        if (take('(')) {
            firstTerm = parseExpression();
            take(')');
        } else if (take('-')) {
            if (between('0', '9')) {
                firstTerm = parseConst(true);
            } else {
                firstTerm = new UnaryMinus(parseMulTerm());
            }
        } else if (between('a', 'z')) {
            firstTerm = parseVariable();
        } else {
            firstTerm = parseConst(false);
        }
        skipWhitespace();
        while (isHighPriority()) {
            String op = parseOperator();
            secondTerm = parseMulTerm();
            firstTerm = makeExpression(firstTerm, secondTerm, op);
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

    private MyExpression parseMulTerm() {
        MyExpression result;
        skipWhitespace();
        if (take('(')) {
            result = parseExpression();
            take(')');
        } else if (between('x', 'z')) {
            result = new Variable(String.valueOf(take()));
        } else if (take('-')) {
            if (between('0', '9')) {
                result = parseConst(true);
            } else {
                result = new UnaryMinus(parseMulTerm());
            }
        } else {
            result = parseConst(false);
        }
        skipWhitespace();
        return result;
    }

    private String parseOperator() {
        StringBuilder op = new StringBuilder();
        while (isOperator()) {
            op.append(take());
            if (operators.contains(op.toString())) {
                return op.toString();
            }
        }
        if (op.toString().length() != 0) {
            throw new NoSuchElementException("There is no operation with name " + op);
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

    private boolean isOperator() {
        return operatorSymbols.contains(ch);
    }

    private boolean isHighPriority() {
        return ch == '*' || ch == '/';
    }
}
