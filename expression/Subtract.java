package expression;

import expression.evaluators.Evaluator;

public class Subtract<T> extends PairMyExpression<T> {
    public Subtract(MyExpression<T> e1, MyExpression<T> e2) {
        super(e1, e2);
    }

    @Override
    protected Priority getPriority() {
        return Priority.ADD;
    }

    @Override
    protected String getOperator() {
        return "-";
    }

    @Override
    protected T pairExpressionEval(T firstResult, T secondResult, Evaluator<T> evaluator) {
        return evaluator.evalSubtract(firstResult, secondResult);
    }

    @Override
    protected int pairExpressionEval(int firstResult, int secondResult) {
        return firstResult - secondResult;
    }

}
