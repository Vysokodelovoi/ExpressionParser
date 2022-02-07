package expression;

import java.math.BigInteger;

public class Maximum extends PairMyExpression {
    public Maximum(MyExpression e1, MyExpression e2) {
        super(e1, e2);

    }

    @Override
    protected Priority getPriority() {
        return Priority.MINMAX;
    }

    @Override
    protected String getOperator() {
        return "max";
    }

    @Override
    protected int pairExpressionEval(int firstResult, int secondResult) {
        return Math.max(firstResult, secondResult);
    }

    @Override
    protected BigInteger pairExpressionEval(BigInteger firstResult, BigInteger secondResult) {
        return null;
    }

    @Override
    protected void fillToString(StringBuilder stringBuilder) {
        super.fillToString(stringBuilder);
    }

    @Override
    protected void fillMiniString(StringBuilder stringBuilder, PairMyExpression parent) {
        e1.fillMiniString(stringBuilder, nullPriorityExpression);
        stringBuilder.append(" max ");
        e2.fillMiniString(stringBuilder, nullPriorityExpression);
    }
}
