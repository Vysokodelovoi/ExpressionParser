package expression.exceptions;

import expression.Divide;
import expression.MyExpression;

public class CheckedDivide extends Divide {

    protected CheckedDivide(MyExpression e1, MyExpression e2) {
        super(e1, e2);
    }

    @Override
    protected int pairExpressionEval(int firstResult, int secondResult) {
        if (secondResult == 0) {
            throw new ZeroDivisionException(toMiniString());
        }
        if (firstResult == Integer.MIN_VALUE && secondResult == -1) {
            throw new OverFlowException(toMiniString(), firstResult + " / " + secondResult);
        }
        return firstResult / secondResult;
    }
}
