package expression;

import java.math.BigInteger;

public class Notz extends UnaryExpression {

    public Notz(MyExpression subExpression) {
        super(subExpression);
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        return null;
    }

    @Override
    public int evaluate(int x) {
        return Integer.numberOfTrailingZeros(subExpression.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return Integer.numberOfTrailingZeros(subExpression.evaluate(x, y, z));
    }

    @Override
    String getOperator() {
        return "t0";
    }
}
