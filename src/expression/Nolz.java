package expression;

import java.math.BigInteger;

public class Nolz extends UnaryExpression {

    public Nolz(MyExpression subExpression) {
        super(subExpression);
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        return null;
    }

    @Override
    public int evaluate(int x) {
        return Integer.numberOfLeadingZeros(subExpression.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return Integer.numberOfLeadingZeros(subExpression.evaluate(x, y, z));
    }

    @Override
    String getOperator() {
        return "l0";
    }
}
