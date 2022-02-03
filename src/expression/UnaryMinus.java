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
        stringBuilder.append("-");
        subExpression.fillToString(stringBuilder);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return -subExpression.evaluate(x, y, z);
    }
}
