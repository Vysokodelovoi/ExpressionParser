package expression;

import java.math.BigInteger;

public class Divide extends PairMyExpression {
    public Divide(MyExpression e1, MyExpression e2) {
        super(e1, e2);
        prior = Priority.MULTIPLY;
        operator = "/";
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
