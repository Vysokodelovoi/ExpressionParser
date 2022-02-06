package expression;

import java.math.BigInteger;

public class Add extends PairMyExpression {
    public Add(MyExpression e1, MyExpression e2) {
        super(e1, e2);
    }

    @Override
    protected Priority getPriority() {
        return Priority.ADD;
    }

    @Override
    protected String getOperator() {
        return "+";
    }

    @Override
    protected int pairExpressionEval(int firstResult, int secondResult) {
        return firstResult + secondResult;
    }

    @Override
    protected BigInteger pairExpressionEval(BigInteger firstResult, BigInteger secondResult) {
        return firstResult.add(secondResult);
    }
}
