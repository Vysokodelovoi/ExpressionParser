package expression;

import java.math.BigInteger;

public class Nolz extends UnaryExpression {

    public Nolz(MyExpression subExpression) {
        super(subExpression);
    }


    @Override
    protected String getOperator() {
        return "l0";
    }

    @Override
    protected int unaryExpressionEval(int exprResult) {
        return Integer.numberOfLeadingZeros(exprResult);
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        return null;
    }
}
