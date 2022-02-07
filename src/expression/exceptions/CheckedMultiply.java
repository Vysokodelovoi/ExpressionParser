package expression.exceptions;

import expression.Multiply;
import expression.MyExpression;

public class CheckedMultiply extends Multiply {
    protected CheckedMultiply(MyExpression e1, MyExpression e2) {
        super(e1, e2);
    }

    @Override
    protected int pairExpressionEval(int firstResult, int secondResult) {
        int result = firstResult * secondResult;
        if (secondResult != 0 && result / secondResult != firstResult ||
                (firstResult == Integer.MIN_VALUE && secondResult == -1) ||
                (secondResult == Integer.MIN_VALUE && firstResult == -1)) {
            throw new OverFlowException(toMiniString(), firstResult + " * " + secondResult);
        }
        return result;
    }
}
