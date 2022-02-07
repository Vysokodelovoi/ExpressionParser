package expression;

import java.math.BigInteger;

public class UnaryMinus extends UnaryExpression {

    public UnaryMinus(MyExpression subExpression) {
        super(subExpression);
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
    public int evaluate(int x, int y, int z) {
        return -subExpression.evaluate(x, y, z);
    }

    @Override
    String getOperator() {
        return "-";
    }
}
