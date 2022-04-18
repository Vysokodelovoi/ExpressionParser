package expression;

import expression.evaluators.Evaluator;

public class Maximum<T> extends PairMyExpression<T> {

    public Maximum(MyExpression<T> e1, MyExpression<T> e2) {
        super(e1, e2);
    }

    @Override
    protected Priority getPriority() {
        return Priority.MINMAX;
    }

    @Override
    protected String getOperator() {
        return "max";
    }

    @Override
    protected T pairExpressionEval(T firstResult, T secondResult, Evaluator<T> evaluator) {
        return evaluator.evalMax(firstResult, secondResult);
    }

    @Override
    protected int pairExpressionEval(int firstResult, int secondResult) {
        return Math.max(firstResult, secondResult);
    }

    @Override
    protected void fillToString(StringBuilder stringBuilder) {
        super.fillToString(stringBuilder);
    }

    @Override
    protected void fillMiniString(StringBuilder stringBuilder, PairMyExpression parent) {
        e1.fillMiniString(stringBuilder, nullPriorityExpression);
        stringBuilder.append(" max ");
        if (e2 instanceof Minimum) {
            stringBuilder.append("(");
            e2.fillMiniString(stringBuilder, nullPriorityExpression);
            stringBuilder.append(")");
        } else {
            e2.fillMiniString(stringBuilder, nullPriorityExpression);
        }
    }
}
