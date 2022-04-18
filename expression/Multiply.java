package expression;

import expression.evaluators.Evaluator;

public class Multiply<T> extends PairMyExpression<T> {

    public Multiply(MyExpression<T> e1, MyExpression<T> e2) {
        super(e1, e2);
    }

    @Override
    protected String getOperator() {
        return "*";
    }

    @Override
    protected T pairExpressionEval(T firstResult, T secondResult, Evaluator<T> evaluator) {
        return evaluator.evalMultiply(firstResult, secondResult);
    }

    @Override
    protected int pairExpressionEval(int firstResult, int secondResult) {
        return firstResult * secondResult;
    }

    @Override
    protected Priority getPriority() {
        return Priority.MULTIPLY;
    }
}
