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
    protected int unaryExpressionEval(int exprResult) {
        return -exprResult;
    }

    @Override
    protected String getOperator() {
        return "-";
    }
}
