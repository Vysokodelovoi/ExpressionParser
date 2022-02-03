package expression;

import java.math.BigInteger;

public class Const extends MyExpression {
    private final int self;
    private final BigInteger bigSelf;

    public Const(int constant) {
        prior = Priority.CONSTANT;
        self = constant;
        bigSelf = null;
    }

    public Const(BigInteger bigInteger) {
        prior = Priority.CONSTANT;
        bigSelf = bigInteger;
        self = 0;
    }

    @Override
    public int evaluate(int value) {
        return self;
    }

    @Override
    public String toString() {
        if (bigSelf == null) {
            return Integer.toString(self);
        }
        return bigSelf.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Const)) {
            return false;
        }
        Const exp = (Const) obj;
        if (bigSelf == null) {
            return exp.self == self;
        }
        return bigSelf.equals(exp.bigSelf);
    }

    @Override
    public int hashCode() {
        return (bigSelf == null) ? self : bigSelf.intValue();
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return self;
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        return bigSelf;
    }

    @Override
    protected void fillToString(StringBuilder stringBuilder) {
        stringBuilder.append(this);
    }
}
