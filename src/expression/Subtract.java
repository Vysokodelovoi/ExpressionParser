package expression;

import java.math.BigInteger;

public class Subtract extends PairMyExpression {
    public Subtract(MyExpression e1, MyExpression e2) {
        super(e1, e2);
    }

    @Override
    protected Priority getPriority() {
        return Priority.ADD;
    }

    @Override
    protected String getOperator() {
        return "-";
    }

    @Override
    protected int pairExpressionEval(int firstResult, int secondResult) {
        return firstResult - secondResult;
    }

    @Override
    protected BigInteger pairExpressionEval(BigInteger firstResult, BigInteger secondResult) {
        return firstResult.subtract(secondResult);
    }
}
