package expression;

import expression.evaluators.Evaluator;

public class Minimum<T> extends PairMyExpression<T> {
    public Minimum(MyExpression<T> e1, MyExpression<T> e2) {
        super(e1, e2);
    }

    @Override
    protected Priority getPriority() {
        return Priority.MINMAX;
    }

    @Override
    protected String getOperator() {
        return "min";
    }

    @Override
    protected T pairExpressionEval(T firstResult, T secondResult, Evaluator<T> evaluator) {
        return evaluator.evalMin(firstResult, secondResult);
    }

    @Override
    protected int pairExpressionEval(int firstResult, int secondResult) {
        return Math.min(firstResult, secondResult);
    }

    @Override
    protected void fillToString(StringBuilder stringBuilder) {
        stringBuilder.append("(");
        e1.fillToString(stringBuilder);
        stringBuilder.append(" min ");
        e2.fillToString(stringBuilder);
        stringBuilder.append(")");
    }

    @Override
    protected void fillMiniString(StringBuilder stringBuilder, PairMyExpression parent) {
        e1.fillMiniString(stringBuilder, nullPriorityExpression);
        stringBuilder.append(" min ");
        if (e2 instanceof Maximum) {
            stringBuilder.append("(");
            e2.fillMiniString(stringBuilder, nullPriorityExpression);
            stringBuilder.append(")");
        } else {
            e2.fillMiniString(stringBuilder, nullPriorityExpression);
        }
    }
}
