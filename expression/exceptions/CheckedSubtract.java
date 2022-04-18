package expression.exceptions;

import expression.MyExpression;
import expression.Subtract;

public class CheckedSubtract extends Subtract {
    public CheckedSubtract(MyExpression e1, MyExpression e2) {
        super(e1, e2);
    }

    @Override
    protected int pairExpressionEval(int firstResult, int secondResult) {
        if ((secondResult <= 0 && Integer.MAX_VALUE + secondResult < firstResult) ||
                (secondResult >= 0 && Integer.MIN_VALUE + secondResult > firstResult)) {
            throw new OverFlowException(this.toMiniString(), firstResult + " - " + secondResult);
        }
        return firstResult - secondResult;
    }
}
