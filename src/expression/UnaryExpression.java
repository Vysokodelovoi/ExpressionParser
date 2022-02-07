package expression;

public abstract class UnaryExpression extends MyExpression {
    protected MyExpression subExpression;

    public UnaryExpression(MyExpression subExpression) {
        this.subExpression = subExpression;
    }

    protected abstract String getOperator();
    @Override
    protected void fillToString(StringBuilder stringBuilder) {
        stringBuilder.append(getOperator()).append("(");
        subExpression.fillToString(stringBuilder);
        stringBuilder.append(")");
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        fillToString(stringBuilder);
        return stringBuilder.toString();
    }

    @Override
    public String toMiniString() {
        if (subExpression instanceof Variable || subExpression instanceof Const
                || subExpression instanceof UnaryExpression) {
            return getOperator()+" "+subExpression.toMiniString();
        }
        return getOperator()+"(" + subExpression.toMiniString() + ")";
    }

    @Override
    protected Priority getPriority() {
        return Priority.CONSTANT;
    }

    @Override
    protected void fillMiniString(StringBuilder stringBuilder, PairMyExpression parent) {
        if (subExpression instanceof Variable || subExpression instanceof Const
                || subExpression instanceof UnaryExpression) {
            stringBuilder.append(getOperator()).append(" ");
            subExpression.fillMiniString(stringBuilder, PairMyExpression.nullPriorityExpression);
        } else {
            stringBuilder.append(getOperator()).append("(");
            subExpression.fillMiniString(stringBuilder, PairMyExpression.nullPriorityExpression);
            stringBuilder.append(")");
        }
    }

    @Override
    protected void fillAsFirst(StringBuilder stringBuilder, PairMyExpression parent) {
        if (subExpression instanceof PairMyExpression) {
            fillMiniString(stringBuilder, (PairMyExpression) subExpression);
        } else {
            fillMiniString(stringBuilder, PairMyExpression.nullPriorityExpression);
        }
    }

    @Override
    protected void fillAsSecond(StringBuilder stringBuilder, PairMyExpression parent) {
        fillAsFirst(stringBuilder, parent);
    }

    @Override
    public int evaluate(int x) {
        return unaryExpressionEval(subExpression.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return unaryExpressionEval(subExpression.evaluate(x, y, z));
    }

    protected abstract int unaryExpressionEval(int exprResult);
}
