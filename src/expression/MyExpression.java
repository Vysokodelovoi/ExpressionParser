package expression;

import java.util.Map;

public abstract class MyExpression implements Expression, TripleExpression, BigIntegerExpression {
    public static final Map<Priority, Integer> priorities = Map.of(
            Priority.ADD, 1,
            Priority.MULTIPLY, 2,
            Priority.CONSTANT, 4,
            Priority.VARIABLE, 4
    );
    public static final Map<String, Boolean> associative = Map.of(
            "+", true,
            "-", false,
            "*", true,
            "/", false
    );
    protected Priority prior;

    @Override
    public String toMiniString() {
        return toString();
    }

//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        fillToString(sb);
//        return sb.toString();
//    }

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
