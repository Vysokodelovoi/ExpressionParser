package expression;

import java.math.BigInteger;

public class Notz extends UnaryExpression {

    public Notz(MyExpression subExpression) {
        super(subExpression);
    }

    @Override
    protected String getOperator() {
        return "t0";
    }

    @Override
    protected int unaryExpressionEval(int exprResult) {
        return Integer.numberOfTrailingZeros(exprResult);
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        return null;
    }
}
