package expression;

import expression.evaluators.Evaluator;

public class Add<T> extends PairMyExpression<T> {
    public Add(final MyExpression<T> e1, final MyExpression<T> e2) {
        super(e1, e2);
    }

    @Override
    protected Priority getPriority() {
        return Priority.ADD;
    }

    @Override
    protected String getOperator() {
        return "+";
    }

    @Override
    protected int pairExpressionEval(final int firstResult, final int secondResult) {
        return firstResult + secondResult;
    }

    @Override
    protected T pairExpressionEval(final T firstResult, final T secondResult, final Evaluator<T> evaluator) {
        return evaluator.evalSum(firstResult, secondResult);
    }
}
