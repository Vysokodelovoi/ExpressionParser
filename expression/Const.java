package expression;

import expression.evaluators.Evaluator;

public class Const<T> extends MyExpression<T> {
    private final int self;

    public Const(int constant) {
        self = constant;
    }

    @Override
    protected Priority getPriority() {
        return Priority.CONSTANT;
    }

    @Override
    public int evaluate(int value) {
        return self;
    }

    @Override
    public String toString() {
        return Integer.toString(self);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Const)) {
            return false;
        }
        Const exp = (Const) obj;
        return exp.self == self;
    }

    @Override
    public int hashCode() {
        return self;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return self;
    }

    @Override
    protected void fillToString(StringBuilder stringBuilder) {
        stringBuilder.append(this);
    }

    @Override
    public T evaluate(T x, T y, T z, Evaluator<T> evaluator) {
        return evaluator.evalConst(self);
    }
}
