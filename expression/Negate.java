package expression;

import expression.evaluators.Evaluator;

public class Negate<T> extends UnaryExpression<T> {

    public Negate(MyExpression<T> subExpression) {
        super(subExpression);
    }

    @Override
    protected int unaryExpressionEval(int exprResult) {
        return -exprResult;
    }

    @Override
    protected String getOperator() {
        return "-";
    }

    @Override
    public T evaluate(T x, T y, T z, Evaluator<T> evaluator) {
        return evaluator.evalNegate(subExpression.evaluate(x, y, z, evaluator));
    }
}
