package expression.exceptions;

import expression.evaluators.Evaluator;
import expression.MyExpression;
import expression.PairMyExpression;
import expression.Priority;

public class CheckedLog<T> extends PairMyExpression<T> {
    public CheckedLog(MyExpression e1, MyExpression e2) {
        super(e1, e2);
    }

    @Override
    protected Priority getPriority() {
        return Priority.POWLOG;
    }

    @Override
    protected String getOperator() {
        return "//";
    }

    @Override
    protected T pairExpressionEval(T firstResult, T secondResult, Evaluator<T> evaluator) {
        return null;
    }

    @Override
    protected int pairExpressionEval(int firstResult, int secondResult) {
        return myLog(firstResult, secondResult);
    }

    private int myLog(int a, int b) {
        if (a < 1 || b <= 0 || b == 1) {
            throw new InvalidArgumentsException("Log :\t" + a + " // " + b);
        }
        int result = 0;
        while (a >= b) {
            a /= b;
            result++;
        }
        return result;
    }
}
