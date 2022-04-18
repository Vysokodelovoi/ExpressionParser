package expression.evaluators;

class FloatEvaluator implements Evaluator<Float> {
    @Override
    public Float evalMultiply(Float first, Float second) {
        return first * second;
    }

    @Override
    public Float evalSum(Float first, Float second) {
        return first + second;
    }

    @Override
    public Float evalDivision(Float first, Float second) {
        return first / second;
    }

    @Override
    public Float evalSubtract(Float first, Float second) {
        return first - second;
    }

    @Override
    public Float evalConst(int value) {
        return (float) value;
    }

    @Override
    public Float evalMax(Float first, Float second) {
        return Math.max(first, second);
    }

    @Override
    public Float evalMin(Float first, Float second) {
        return Math.min(first, second);
    }

    @Override
    public Float evalAbs(Float expr) {
        return Math.abs(expr);
    }

    @Override
    public Float evalNegate(Float expr) {
        return -expr;
    }

    @Override
    public Float evalCount(Float expr) {
        return (float) Integer.bitCount(Float.floatToIntBits(expr));
    }
}
