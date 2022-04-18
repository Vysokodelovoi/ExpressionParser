package expression.evaluators;

import java.math.BigInteger;

class BigIntegerEvaluator implements Evaluator<BigInteger> {
    @Override
    public BigInteger evalMultiply(BigInteger firstRes, BigInteger secondRes) {
        return firstRes.multiply(secondRes);
    }

    @Override
    public BigInteger evalSum(BigInteger firstRes, BigInteger secondRes) {
        return firstRes.add(secondRes);
    }

    @Override
    public BigInteger evalDivision(BigInteger firstRes, BigInteger secondRes) {
        return firstRes.divide(secondRes);
    }

    @Override
    public BigInteger evalSubtract(BigInteger firstRes, BigInteger secondRes) {
        return firstRes.subtract(secondRes);
    }

    @Override
    public BigInteger evalConst(int value) {
        return BigInteger.valueOf(value);
    }

    @Override
    public BigInteger evalMax(BigInteger first, BigInteger second) {
        return first.max(second);
    }

    @Override
    public BigInteger evalMin(BigInteger first, BigInteger second) {
        return first.min(second);
    }

    @Override
    public BigInteger evalAbs(BigInteger expr) {
        return expr.abs();
    }

    @Override
    public BigInteger evalNegate(BigInteger expr) {
        return expr.negate();
    }

    @Override
    public BigInteger evalCount(BigInteger expr) {
        return BigInteger.valueOf(expr.bitCount());
    }
}
