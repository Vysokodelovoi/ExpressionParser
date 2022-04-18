package expression.evaluators;

import java.math.BigInteger;

public class NumberEvaluators {
    public static final Evaluator<Integer> INT_EVAL = new IntegerEvaluator();
    public static final Evaluator<Integer> UNCHECKED_INT_EVAL = new UncheckedIntegerEvaluator();
    public static final Evaluator<BigInteger> BIGINT_EVAL = new BigIntegerEvaluator();
    public static final Evaluator<Double> DOUBLE_EVAL = new DoubleEvaluator();
    public static final Evaluator<Float> FLOAT_EVAL = new FloatEvaluator();
    public static final Evaluator<Long> LONG_EVAL = new LongEvaluator();
}
