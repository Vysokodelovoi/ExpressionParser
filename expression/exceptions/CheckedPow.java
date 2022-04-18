package expression.exceptions;

import expression.evaluators.Evaluator;
import expression.MyExpression;
import expression.PairMyExpression;
import expression.Priority;

public class CheckedPow<T> extends PairMyExpression<T> {
    public CheckedPow(MyExpression e1, MyExpression e2) {
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
    protected T pairExpressionEval(T firstResult, T secondResult, Evaluator<T> evaluator) {
        return null;
    }

    @Override
    protected int pairExpressionEval(int firstResult, int secondResult) {
        return myPow(firstResult, secondResult);
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
            if (CheckedMultiply.hasOverflow(cached, cached)) {
                throw new OverFlowException(toMiniString(), "The value is too long");
            }
        } else {
            result = cached * cached * x;
            if (CheckedMultiply.hasOverflow(cached, cached) || CheckedMultiply.hasOverflow(cached * cached, x)) {
                throw new OverFlowException(toMiniString(), "The value is too long");
            }
        }
        return result;
    }
}
