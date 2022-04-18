package expression;

import expression.evaluators.Evaluator;

public class Divide<T> extends PairMyExpression<T> {
    public Divide(MyExpression<T> e1, MyExpression<T> e2) {
        super(e1, e2);
    }

    @Override
    protected Priority getPriority() {
        return Priority.MULTIPLY;
    }

    @Override
    protected String getOperator() {
        return "/";
    }

    @Override
    protected T pairExpressionEval(T firstResult, T secondResult, Evaluator<T> evaluator) {
        return evaluator.evalDivision(firstResult, secondResult);
    }

    @Override
    protected int pairExpressionEval(int firstResult, int secondResult) {
        if (secondResult == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return firstResult / secondResult;
    }
}
