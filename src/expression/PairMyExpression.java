package expression;

import java.math.BigInteger;
import java.util.Objects;

public abstract class PairMyExpression extends MyExpression {
    protected final MyExpression e1, e2;
    public final static PairMyExpression nullPriorityExpression =
            new PairMyExpression(null, null) {
        @Override
        public Priority getPriority() {
            return Priority.ADD;
        }

        @Override
        protected String getOperator() {
            return "+";
        }

        @Override
        protected int pairExpressionEval(int firstResult, int secondResult) {
            return 0;
        }

        @Override
        protected BigInteger pairExpressionEval(BigInteger firstResult, BigInteger secondResult) {
            return null;
        }
    };


    protected abstract String getOperator();
    protected abstract int pairExpressionEval(int firstResult, int secondResult);

    protected abstract BigInteger pairExpressionEval(BigInteger firstResult, BigInteger secondResult);

    protected PairMyExpression(MyExpression e1, MyExpression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public String toString() {
        StringBuilder ans = new StringBuilder();
        fillToString(ans);
        return ans.toString();
    }

    @Override
    public String toMiniString() {
        StringBuilder stringBuilder = new StringBuilder();
        fillMiniString(stringBuilder, this);
        return stringBuilder.toString();
    }

    @Override
    protected void fillToString(StringBuilder stringBuilder) {
        stringBuilder.append("(");
        e1.fillToString(stringBuilder);
        stringBuilder.append(" ").append(getOperator()).append(" ");
        e2.fillToString(stringBuilder);
        stringBuilder.append(")");
    }

    @Override
    protected void fillMiniString(StringBuilder stringBuilder, PairMyExpression parent) {
        e1.fillAsFirst(stringBuilder, this);
        stringBuilder.append(" ").append(getOperator()).append(" ");
        e2.fillAsSecond(stringBuilder, this);
    }

    protected void fillAsFirst(StringBuilder stringBuilder, PairMyExpression parent) {
        boolean needParenthesis = priorities.get(parent.getPriority()) > priorities.get(getPriority());
        decorate(stringBuilder, needParenthesis);
    }

    protected void fillAsSecond(StringBuilder stringBuilder, PairMyExpression parent) {
        String operator = getOperator();
        boolean needParenthesis = priorities.get(parent.getPriority()) > priorities.get(getPriority());
        needParenthesis = needParenthesis || (parent.getOperator().equals(operator)
                && !associative.get(operator)) || (getPriority() == parent.getPriority()
                && !operator.equals(parent.getOperator())
                && !operator.equals("-"));
        decorate(stringBuilder, needParenthesis);
    }

    protected void decorate(StringBuilder stringBuilder, boolean needParenthesis) {
        if (needParenthesis) {
            stringBuilder.append("(");
        }
        fillMiniString(stringBuilder, this);
        if (needParenthesis) {
            stringBuilder.append(")");
        }
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        return pairExpressionEval(e1.evaluate(x), e2.evaluate(x));
    }

    @Override
    public int evaluate(int x) {
        return pairExpressionEval(e1.evaluate(x), e2.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return pairExpressionEval(e1.evaluate(x, y, z), e2.evaluate(x, y, z));
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PairMyExpression)) {
            return false;
        }
        PairMyExpression exp = (PairMyExpression) obj;
        return getOperator().equals(exp.getOperator()) && e1.equals(exp.e1) && e2.equals(exp.e2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(e1, e2, getOperator());
    }
}
