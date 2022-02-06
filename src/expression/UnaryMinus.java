package expression;

import java.math.BigInteger;

public class UnaryMinus extends MyExpression {
    MyExpression subExpression;

    public UnaryMinus(MyExpression subExpression) {
        this.subExpression = subExpression;
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        return subExpression.evaluate(x).negate();
    }

    @Override
    public int evaluate(int x) {
        return -subExpression.evaluate(x);
    }

    @Override
    protected void fillToString(StringBuilder stringBuilder) {
        stringBuilder.append("-(");
        subExpression.fillToString(stringBuilder);
        stringBuilder.append(")");
    }

    @Override
    public String toString() {
        return "-(" + subExpression.toString() + ")";
    }

    @Override
    protected Priority getPriority() {
        return Priority.CONSTANT;
    }

    @Override
    public String toMiniString() {
        if (subExpression instanceof Variable || subExpression instanceof Const
                || subExpression instanceof UnaryMinus) {
            return "- "+subExpression.toMiniString();
        }
        return "-(" + subExpression.toMiniString() + ")";
    }

    @Override
    protected void fillMiniString(StringBuilder stringBuilder, PairMyExpression parent) {
        if (subExpression instanceof Variable || subExpression instanceof Const
                || subExpression instanceof UnaryMinus) {
            stringBuilder.append("- ");
//            subExpression.fillMiniString(stringBuilder, parent);
            subExpression.fillMiniString(stringBuilder, PairMyExpression.nullPriorityExpression);

        } else {
            stringBuilder.append("-(");
            subExpression.fillMiniString(stringBuilder, PairMyExpression.nullPriorityExpression);
            stringBuilder.append(")");
        }
    }

    @Override
    protected void fillAsFirst(StringBuilder stringBuilder, PairMyExpression parent) {
        if (subExpression instanceof PairMyExpression) {
            fillMiniString(stringBuilder, (PairMyExpression) subExpression);
        } else {
            fillMiniString(stringBuilder, PairMyExpression.nullPriorityExpression);
        }
    }

    @Override
    protected void fillAsSecond(StringBuilder stringBuilder, PairMyExpression parent) {
        fillAsFirst(stringBuilder, parent);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return -subExpression.evaluate(x, y, z);
    }
}
