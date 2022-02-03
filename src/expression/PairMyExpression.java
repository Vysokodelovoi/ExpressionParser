package expression;

import java.math.BigInteger;
import java.util.Objects;

public abstract class PairMyExpression extends MyExpression {
    protected final MyExpression e1, e2;
    protected String operator;

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
        stringBuilder.append(" ").append(operator).append(" ");
        e2.fillToString(stringBuilder);
        stringBuilder.append(")");
    }

    @Override
    protected void fillMiniString(StringBuilder stringBuilder, PairMyExpression parent) {
        e1.fillAsFirst(stringBuilder, parent);
        stringBuilder.append(" ").append(operator).append(" ");
        e2.fillAsSecond(stringBuilder, parent);
    }

    protected void fillAsFirst(StringBuilder stringBuilder, PairMyExpression parent) {
        boolean needParenthesis = priorities.get(parent.prior) > priorities.get(prior);
        decorate(stringBuilder, needParenthesis);
    }

    protected void fillAsSecond(StringBuilder stringBuilder, PairMyExpression parent) {
        boolean needParenthesis = priorities.get(parent.prior) > priorities.get(prior);
        needParenthesis = needParenthesis || (parent.operator.equals(operator)
                && !associative.get(operator)) || (prior == parent.prior
                && !operator.equals(parent.operator)
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
        return operator.equals(exp.operator) && e1.equals(exp.e1) && e2.equals(exp.e2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(e1, e2, operator);
    }
}
