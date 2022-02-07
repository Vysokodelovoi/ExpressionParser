package expression.exceptions;

import expression.Add;
import expression.MyExpression;

import java.math.BigInteger;

public class CheckedAdd extends Add {
    protected CheckedAdd(MyExpression e1, MyExpression e2) {
        super(e1, e2);
    }

    @Override
    protected int pairExpressionEval(int firstResult, int secondResult) {
        int res = firstResult + secondResult;

        if ((res > firstResult && secondResult < 0)  || (res < firstResult && secondResult > 0) ) {
            throw new OverFlowException(this.toMiniString(), firstResult + " + " + secondResult);
        }
        return res;
    }
}
