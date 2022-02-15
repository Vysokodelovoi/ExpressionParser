package expression.exceptions;

import expression.MyExpression;
import expression.UnaryExpression;

import java.math.BigInteger;

public class Abs extends UnaryExpression {
    public Abs(MyExpression subExpression) {
        super(subExpression);
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        return subExpression.evaluate(x).abs();
    }

    @Override
    protected String getOperator() {
        return "abs";
    }

    @Override
    protected int unaryExpressionEval(int exprResult) {
        return myAbs(exprResult);
    }

    private int myAbs(int x) {
        if (x == Integer.MIN_VALUE) {
            throw new OverFlowException(toMiniString(), "abs " + x);
        }
        if (x < 0) return -x;
        return x;
    }
}
