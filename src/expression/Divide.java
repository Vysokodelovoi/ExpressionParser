package expression;

import java.math.BigInteger;

public class Divide extends PairMyExpression {
    public Divide(MyExpression e1, MyExpression e2) {
        super(e1, e2);
    }

    @Override
    protected Priority getPriority() {
        return Priority.MULTIPLY;
    }

    @Override
    protected String getOperator() {
        return "/";
    }

    @Override
    protected int pairExpressionEval(int firstResult, int secondResult) {
        if (secondResult == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return firstResult / secondResult;
    }

    @Override
    protected BigInteger pairExpressionEval(BigInteger firstResult, BigInteger secondResult) {
        return firstResult.divide(secondResult);
    }
}
