package expression.evaluators;

class DoubleEvaluator implements Evaluator<Double> {
    @Override
    public Double evalMultiply(Double firstRes, Double secondRes) {
        return firstRes * secondRes;
    }

    @Override
    public Double evalSum(Double firstRes, Double secondRes) {
        return firstRes + secondRes;
    }

    @Override
    public Double evalDivision(Double firstRes, Double secondRes) {
        return firstRes / secondRes;
    }

    @Override
    public Double evalSubtract(Double firstRes, Double secondRes) {
        return firstRes - secondRes;
    }

    @Override
    public Double evalConst(int value) {
        return (double) value;
    }

    @Override
    public Double evalMax(Double first, Double second) {
        return Double.max(first, second);
    }

    @Override
    public Double evalMin(Double first, Double second) {
        return Double.min(first, second);
    }

    @Override
    public Double evalAbs(Double expr) {
        return Math.abs(expr);
    }

    @Override
    public Double evalNegate(Double expr) {
        return -expr;
    }

    @Override
    public Double evalCount(Double expr) {
        return (double) Long.bitCount(Double.doubleToLongBits(expr));
    }
}
