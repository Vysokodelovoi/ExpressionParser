package expression;

import java.math.BigInteger;

public class Minimum extends PairMyExpression {
    public Minimum(MyExpression e1, MyExpression e2) {
        super(e1, e2);
    }

    @Override
    protected Priority getPriority() {
        return Priority.MINMAX;
    }

    @Override
    protected String getOperator() {
        return "min";
    }

    @Override
    protected int pairExpressionEval(int firstResult, int secondResult) {
        return Math.min(firstResult, secondResult);
    }

    @Override
    protected BigInteger pairExpressionEval(BigInteger firstResult, BigInteger secondResult) {
        return null;
    }

    @Override
    protected void fillToString(StringBuilder stringBuilder) {
        stringBuilder.append("(");
        e1.fillToString(stringBuilder);
        stringBuilder.append(" min ");
        e2.fillToString(stringBuilder);
        stringBuilder.append(")");
    }

    @Override
    protected void fillMiniString(StringBuilder stringBuilder, PairMyExpression parent) {
        e1.fillMiniString(stringBuilder, nullPriorityExpression);
        stringBuilder.append(" min ");
        e2.fillMiniString(stringBuilder, nullPriorityExpression);
    }
}
