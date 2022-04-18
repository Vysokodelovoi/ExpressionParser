package expression;

import expression.evaluators.Evaluator;

public class Variable<T> extends MyExpression<T> {
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    protected Priority getPriority() {
        return Priority.VARIABLE;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Variable)) {
            return false;
        }
        return ((Variable) obj).name.equals(name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return switch (name) {
            case "x" -> x;
            case "y" -> y;
            case "z" -> z;
            default -> throw new UnsupportedOperationException("Unable to evaluate variable " + name);
        };
    }

    @Override
    public int evaluate(int value) {
        return value;
    }

    @Override
    protected void fillToString(StringBuilder stringBuilder) {
        stringBuilder.append(name);
    }

    @Override
    public T evaluate(T x, T y, T z, Evaluator<T> evaluator) {
        return switch (name) {
            case "x" -> x;
            case "y" -> y;
            case "z" -> z;
            default -> throw new UnsupportedOperationException("Unable to evaluate variable " + name);
        };
    }
}
