package expression.exceptions;

import expression.Add;
import expression.MyExpression;

public class CheckedAdd extends Add {
    public CheckedAdd(MyExpression e1, MyExpression e2) {
        super(e1, e2);
    }

    @Override
    protected int pairExpressionEval(int firstResult, int secondResult) {
        if ((firstResult > 0 && Integer.MAX_VALUE - firstResult < secondResult) ||
                (secondResult < 0 && Integer.MIN_VALUE - secondResult > firstResult)){
            throw new OverFlowException(this.toMiniString(), firstResult + " + " + secondResult);
        }
        return firstResult + secondResult;
    }
}
