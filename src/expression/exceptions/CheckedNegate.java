package expression.exceptions;

import expression.MyExpression;
import expression.Negate;

import java.math.BigInteger;

public class CheckedNegate extends Negate {

    public CheckedNegate(MyExpression subExpression) {
        super(subExpression);
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        return subExpression.evaluate(x).negate();
    }

    @Override
    protected int unaryExpressionEval(int exprResult) {
        if (exprResult == Integer.MIN_VALUE) {
            throw new OverFlowException(toMiniString(), "- " + exprResult);
        }
        return -exprResult;
    }
}
