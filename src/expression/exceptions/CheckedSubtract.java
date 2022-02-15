package expression.exceptions;

import expression.MyExpression;
import expression.Subtract;

public class CheckedSubtract extends Subtract {

    public CheckedSubtract(MyExpression e1, MyExpression e2) {
        super(e1, e2);
    }

    @Override
    protected int pairExpressionEval(int firstResult, int secondResult) {
        int res = firstResult - secondResult;
        if ((res > firstResult && secondResult > 0) || (res < firstResult && secondResult < 0)) {
            throw new OverFlowException(toMiniString(), firstResult + " - " + secondResult);
        }
        return firstResult - secondResult;
    }
}
