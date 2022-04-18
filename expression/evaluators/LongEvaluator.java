package expression.evaluators;

class LongEvaluator implements Evaluator<Long> {
    @Override
    public Long evalMultiply(Long first, Long second) {
        return first * second;
    }

    @Override
    public Long evalSum(Long first, Long second) {
        return first + second;
    }

    @Override
    public Long evalDivision(Long first, Long second) {
        return first / second;
    }

    @Override
    public Long evalSubtract(Long first, Long second) {
        return first - second;
    }

    @Override
    public Long evalConst(int value) {
        return (long) value;
    }

    @Override
    public Long evalMax(Long first, Long second) {
        return Math.max(first, second);
    }

    @Override
    public Long evalMin(Long first, Long second) {
        return Math.min(first, second);
    }

    @Override
    public Long evalAbs(Long expr) {
        return Math.abs(expr);
    }

    @Override
    public Long evalNegate(Long expr) {
        return -expr;
    }

    @Override
    public Long evalCount(Long expr) {
        return (long) Long.bitCount(expr);
    }
}
