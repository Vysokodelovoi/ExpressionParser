package expression;

import java.math.BigInteger;

public class Multiply extends PairMyExpression {

    public Multiply(MyExpression e1, MyExpression e2) {
        super(e1, e2);
        prior = Priority.MULTIPLY;
        operator = "*";
    }

    @Override
    protected int pairExpressionEval(int firstResult, int secondResult) {
        return firstResult * secondResult;
    }

    @Override
    protected BigInteger pairExpressionEval(BigInteger firstResult, BigInteger secondResult) {
        return firstResult.multiply(secondResult);
    }
}
