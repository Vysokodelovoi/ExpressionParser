package expression;

import java.math.BigInteger;

public class Variable extends MyExpression {
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    void checkName() {
        if (!name.equals("x")) {
            throw new UnsupportedOperationException("Unable to evaluate variable " + name);
        }
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
    public BigInteger evaluate(BigInteger x) {
        checkName();
        return x;
    }

    @Override
    public int evaluate(int value) {
        checkName();
        return value;
    }

    @Override
    protected void fillToString(StringBuilder stringBuilder) {
        stringBuilder.append(name);
    }
}
