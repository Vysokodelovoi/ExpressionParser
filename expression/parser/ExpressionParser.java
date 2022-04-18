package expression.parser;

import expression.*;
import expression.exceptions.Abs;
import expression.exceptions.ParseExceptions;
import expression.exceptions.UnknownTokenException;
import expression.parser.BaseParser.BaseParser;
import expression.parser.BaseParser.StringSource;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class ExpressionParser<T> extends BaseParser {
    private final Map<String, BinaryOperator<MyExpression<T>>> BINARY_OPERATOR_MAP = Map.of(
            "+", Add::new,
            "-", Subtract::new,
            "*", Multiply::new,
            "/", Divide::new,
            "min", Minimum::new,
            "max", Maximum::new
    );
    private final Map<String, UnaryOperator<MyExpression<T>>> UNARY_OPERATOR_MAP = Map.of(
            "abs", Abs::new,
            "count", Count::new
    );
    private final static Map<Integer, Set<String>> OPERATORS_BY_PRIORITY = Map.of(
        0, Set.of("min", "max"),
        1, Set.of("+", "-"),
        2, Set.of("*", "/")
    );

    public MyExpression<T> parse(final String s) throws ParseExceptions {
        init(new StringSource(s));
        return parseExpr(0);
    }

    private MyExpression<T> parseArg() throws UnknownTokenException {
        skipWhitespace();
        if (take('(')) {
            MyExpression<T> expr =  parseExpr(0);
            take(')');
            return expr;
        } else if (take('-')) {
            if (between('0', '9')) {
                return parseConst(true);
            }
            return new Negate<>(parseArg());
        } else if (between('0', '9')) {
            return parseConst(false);
        } else if (between('x', 'z')) {
            return parseVariable();
        } else {
            String op = getUnary();
            if (op == null) {
                throw new UnknownTokenException("Value or unary operator expected", getPosition());
            }
            return makeUnary(op, parseArg());
        }
    }

    private MyExpression<T> parseExpr(final int priority) throws UnknownTokenException {
        if (priority > 2) {
            return parseArg();
        }
        MyExpression<T> result = parseExpr(priority+1);
        while (true) {
            skipWhitespace();
            String operation = getOperator(priority);
            if (operation == null) {
                return result;
            }
            result = makeBinary(result, parseExpr(priority+1), operation);
        }
    }

    private Const<T> parseConst(boolean negative) {
        StringBuilder sb = new StringBuilder();
        if (negative) {
            sb.append("-");
        }
        while (between('0', '9')) {
            sb.append(take());
        }
        return new Const<>(Integer.parseInt(sb.toString()));
    }

    private Variable<T> parseVariable() {
        return new Variable<>(String.valueOf(take()));
    }

    private String getOperator(final int priority) {
        for (String op : OPERATORS_BY_PRIORITY.get(priority)) {
            if (test(op)) {
                return op;
            }
        }
        return null;
    }

    private String getUnary() {
        for (final String s : UNARY_OPERATOR_MAP.keySet()) {
            if (test(s)) {
                return s;
            }
        }
        return null;
    }
    private MyExpression<T> makeBinary(final MyExpression<T> first, final MyExpression<T> second, final String curOperation) {
        // :NOTE: Map<String, BinaryOperator<MyExpression<T>>
        // *fixed*
        MyExpression<T> result = BINARY_OPERATOR_MAP.get(curOperation).apply(first, second);
        if (result == null) {
            throw new NoSuchElementException("Cannot resolve unary operation " + curOperation);
        }
        return result;
    }

    private MyExpression<T> makeUnary(final String op, final MyExpression<T> subExpression) {
        MyExpression<T> result = UNARY_OPERATOR_MAP.get(op).apply(subExpression);
        if (result == null) {
            throw new NoSuchElementException("Cannot resolve unary operation " + op);
        }
        return result;
    }
}
