package expression.exceptions;

import expression.evaluators.Evaluator;
import expression.MyExpression;
import expression.UnaryExpression;

public class Abs<T> extends UnaryExpression<T> {
    public Abs(MyExpression<T> subExpression) {
        super(subExpression);
    }

    @Override
    protected String getOperator() {
        return "abs";
    }

    @Override
    protected int unaryExpressionEval(int exprResult) {
        return myAbs(exprResult);
    }

    private int myAbs(int x) {
        if (x == Integer.MIN_VALUE) {
            throw new OverFlowException(toMiniString(), "abs " + x);
        }
        if (x < 0) return -x;
        return x;
    }

    @Override
    public T evaluate(T x, T y, T z, Evaluator<T> evaluator) {
        return evaluator.evalAbs(subExpression.evaluate(x, y, z, evaluator));
    }
}
