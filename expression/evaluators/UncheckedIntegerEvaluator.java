package expression.evaluators;

class UncheckedIntegerEvaluator implements Evaluator<Integer> {
    @Override
    public Integer evalMultiply(Integer first, Integer second) {
        return first * second;
    }

    @Override
    public Integer evalSum(Integer first, Integer second) {
        return first + second;
    }

    @Override
    public Integer evalDivision(Integer first, Integer second) {
        return first / second;
    }

    @Override
    public Integer evalSubtract(Integer first, Integer second) {
        return first - second;
    }

    @Override
    public Integer evalConst(int value) {
        return value;
    }

    @Override
    public Integer evalMax(Integer first, Integer second) {
        return Integer.max(first, second);
    }

    @Override
    public Integer evalMin(Integer first, Integer second) {
        return Integer.min(first, second);
    }

    @Override
    public Integer evalAbs(Integer expr) {
        return Math.abs(expr);
    }

    @Override
    public Integer evalNegate(Integer expr) {
        return -expr;
    }

    @Override
    public Integer evalCount(Integer expr) {
        return Integer.bitCount(expr);
    }
}
