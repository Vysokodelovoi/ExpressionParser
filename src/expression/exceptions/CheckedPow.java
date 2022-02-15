package expression.exceptions;

import expression.MyExpression;
import expression.PairMyExpression;
import expression.Priority;

import java.math.BigInteger;

public class CheckedPow extends PairMyExpression {
    protected CheckedPow(MyExpression e1, MyExpression e2) {
        super(e1, e2);
    }

    @Override
    protected Priority getPriority() {
        return Priority.POWLOG;
    }

    @Override
    protected String getOperator() {
        return "**";
    }

    @Override
    protected int pairExpressionEval(int firstResult, int secondResult) {
        return myPow(firstResult, secondResult);
    }
    private boolean hasOverflow(int a, int b) {
        int result = a * b;
        return b != 0 && (a != (result / b) || b != (result / a));
    }
    private int myPow(int x, int p) {
        if (x == 0 && p == 0 || p < 0) {
            throw new InvalidArgumentsException("Pow(" + x + ", "+p + ")");
        }
        if (x == 0) {
            return  0;
        }
        if (p == 0) {
            return 1;
        }
        int cached = myPow(x, p / 2);
        int result;
        if (p % 2 == 0) {
            result = cached * cached;
            if (hasOverflow(cached, cached)) {
                throw new OverFlowException(toMiniString(), "The value is too long");
            }
        } else {
            result = cached * cached * x;
            if (hasOverflow(cached, cached) || hasOverflow(cached * cached, x)) {
                throw new OverFlowException(toMiniString(), "The value is too long");
            }
        }
        return result;
    }

    @Override
    protected BigInteger pairExpressionEval(BigInteger firstResult, BigInteger secondResult) {
        return null;
    }
}
