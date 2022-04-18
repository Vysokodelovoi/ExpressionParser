package expression.exceptions;

import expression.Multiply;
import expression.MyExpression;

public class CheckedMultiply extends Multiply {
    public CheckedMultiply(MyExpression e1, MyExpression e2) {
        super(e1, e2);
    }

    public static boolean hasOverflow(int a, int b) {
        if (b == 0) {
            return false;
        }
        if (b == -1) {
            return a == Integer.MIN_VALUE;
        }
        if (b > 0 && (a > 0 && Integer.MAX_VALUE / b < a ||
                a < 0 && Integer.MIN_VALUE / b > a)) {
            return true;
        }
        return b < 0 && (a > 0 && Integer.MIN_VALUE / b < a ||
                a < 0 && Integer.MAX_VALUE / b > a);
    }

    @Override
    protected int pairExpressionEval(int firstResult, int secondResult) {
        if (hasOverflow(firstResult, secondResult)) {
            throw new OverFlowException(toMiniString(), firstResult + " * " + secondResult);
        }
        return firstResult * secondResult;
    }
}
