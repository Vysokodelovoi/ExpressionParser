package expression;

import expression.evaluators.Evaluator;

import java.util.Objects;

public abstract class PairMyExpression<T> extends MyExpression<T> {
    protected final MyExpression<T> e1, e2;
    public final static PairMyExpression<Object> nullPriorityExpression =
            new PairMyExpression<>( null, null) {
                @Override
                public int evaluate(final int x, final int y, final int z) {
                    return 0;
                }

        @Override
        public Object evaluate(final Object x, final Object y, final Object z, final Evaluator<Object> e) {
            return null;
        }

        @Override
        public Priority getPriority() {
            return Priority.ADD;
        }

        @Override
        protected String getOperator() {
            return "+";
        }

        @Override
        protected Object pairExpressionEval(final Object f, final Object s, final Evaluator<Object> e)  {
            return 0;
        }

        @Override
        public int pairExpressionEval(final int f, final int s) {
            return 0;
        }
    };


    protected abstract String getOperator();
    protected abstract T pairExpressionEval(T firstResult, T secondResult, Evaluator<T> evaluator);

    protected PairMyExpression(final MyExpression<T> e1, final MyExpression<T> e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public String toString() {
        final StringBuilder ans = new StringBuilder();
        fillToString(ans);
        return ans.toString();
    }

    @Override
    public String toMiniString() {
        final StringBuilder stringBuilder = new StringBuilder();
        fillMiniString(stringBuilder, this);
        return stringBuilder.toString();
    }

    @Override
    protected void fillToString(final StringBuilder stringBuilder) {
        stringBuilder.append("(");
        e1.fillToString(stringBuilder);
        stringBuilder.append(" ").append(getOperator()).append(" ");
        e2.fillToString(stringBuilder);
        stringBuilder.append(")");
    }

    @Override
    protected void fillMiniString(final StringBuilder stringBuilder, final PairMyExpression parent) {
        e1.fillAsFirst(stringBuilder, this);
        stringBuilder.append(" ").append(getOperator()).append(" ");
        e2.fillAsSecond(stringBuilder, this);
    }

    protected void fillAsFirst(final StringBuilder stringBuilder, final PairMyExpression parent) {
        final boolean needParenthesis = priorities.get(parent.getPriority()) > priorities.get(getPriority());
        decorate(stringBuilder, needParenthesis);
    }

    protected void fillAsSecond(final StringBuilder stringBuilder, final PairMyExpression parent) {
        final String operator = getOperator();
        boolean needParenthesis = priorities.get(parent.getPriority()) > priorities.get(getPriority());
        needParenthesis = needParenthesis || (parent.getOperator().equals(operator)
                && !associative.get(operator)) || (getPriority() == parent.getPriority()
                && !operator.equals(parent.getOperator())
                && !operator.equals("-"));
        decorate(stringBuilder, needParenthesis);
    }

    protected void decorate(final StringBuilder stringBuilder, final boolean needParenthesis) {
        if (needParenthesis) {
            stringBuilder.append("(");
        }
        fillMiniString(stringBuilder, this);
        if (needParenthesis) {
            stringBuilder.append(")");
        }
    }

    @Override
    public int evaluate(final int x) {
        return pairExpressionEval(e1.evaluate(x), e2.evaluate(x));
    }

    @Override
    public int evaluate(final int x, final int y, final int z) {
        return pairExpressionEval(e1.evaluate(x, y, z), e2.evaluate(x, y, z));
    }

    protected abstract int pairExpressionEval(int f, int s);
    @Override
    public T evaluate(final T x, final T y, final T z, final Evaluator<T> evaluator) {
        return pairExpressionEval(e1.evaluate(x, y, z, evaluator), e2.evaluate(x, y, z, evaluator), evaluator);
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof PairMyExpression)) {
            return false;
        }
        final PairMyExpression exp = (PairMyExpression) obj;
        return getOperator().equals(exp.getOperator()) && e1.equals(exp.e1) && e2.equals(exp.e2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(e1, e2, getOperator());
    }
}
