package expression.evaluators;

import expression.exceptions.OverFlowException;
import expression.exceptions.ZeroDivisionException;

class IntegerEvaluator implements Evaluator<Integer> {
    @Override
    public Integer evalMultiply(Integer firstRes, Integer secondRes) {
        int result = firstRes * secondRes;
        if (secondRes != 0 && result / secondRes != firstRes) {
            throw new OverFlowException("", firstRes +" * " +secondRes);
        }
        return firstRes * secondRes;
    }

    @Override
    public Integer evalSum(Integer firstResult, Integer secondResult) {
        if ((firstResult > 0 && Integer.MAX_VALUE - firstResult < secondResult) ||
                (secondResult < 0 && Integer.MIN_VALUE - secondResult > firstResult)){
            throw new OverFlowException("", firstResult + " + " + secondResult);
        }
        return firstResult + secondResult;
    }

    @Override
    public Integer evalDivision(Integer firstRes, Integer secondRes) {
        if (secondRes == 0) {
            throw new ZeroDivisionException(firstRes + " / " + secondRes);
        }
        if (firstRes == Integer.MIN_VALUE && secondRes == -1) {
            throw new OverFlowException("", firstRes + " / " + secondRes);
        }
        return firstRes / secondRes;
    }

    @Override
    public Integer evalSubtract(Integer firstRes, Integer secondRes) {
        if ((secondRes <= 0 && Integer.MAX_VALUE + secondRes < firstRes) ||
                (secondRes >= 0 && Integer.MIN_VALUE + secondRes > firstRes)) {
            throw new OverFlowException("", firstRes + " - " + secondRes);
        }
        return firstRes - secondRes;
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
        if (expr == Integer.MIN_VALUE) {
            throw new OverFlowException("", "- " + expr);
        }
        return -expr;
    }

    @Override
    public Integer evalCount(Integer expr) {
        return Integer.bitCount(expr);
    }
}
