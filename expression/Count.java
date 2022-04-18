package expression;

import expression.evaluators.Evaluator;

public class Count<T> extends UnaryExpression<T> {

    public Count(MyExpression<T> subExpression) {
        super(subExpression);
    }

    @Override
    public T evaluate(T x, T y, T z, Evaluator<T> evaluator) {
        return evaluator.evalCount(subExpression.evaluate(x, y, z, evaluator));
    }

    @Override
    protected String getOperator() {
        return "count";
    }

    @Override
    protected int unaryExpressionEval(int exprResult) {
        return 0;
    }
}
