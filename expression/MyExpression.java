package expression;

import java.util.Map;

public abstract class MyExpression<T> implements Expression, TripleExpression, Evaluatable<T> {
    public static final Map<Priority, Integer> priorities = Map.of(
            Priority.ADD, 1,
            Priority.MULTIPLY, 2,
            Priority.CONSTANT, 4,
            Priority.VARIABLE, 4,
            Priority.MINMAX, 0,
            Priority.POWLOG, 5
    );
    public static final Map<String, Boolean> associative = Map.of(
            "+", true,
            "-", false,
            "*", true,
            "/", false,
            "min", true,
            "max", true,
            "**", false,
            "//", false
    );
    protected abstract Priority getPriority();

    @Override
    public String toMiniString() {
        return toString();
    }

    protected abstract void fillToString(StringBuilder stringBuilder);

    protected void fillMiniString(StringBuilder stringBuilder, PairMyExpression parent) {
        stringBuilder.append(this);
    }

    protected void fillAsFirst(StringBuilder stringBuilder, PairMyExpression parent) {
        stringBuilder.append(this);
    }

    protected void fillAsSecond(StringBuilder stringBuilder, PairMyExpression parent) {
        stringBuilder.append(this);
    }
}
