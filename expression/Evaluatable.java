package expression;


import expression.evaluators.Evaluator;

public interface Evaluatable<T> extends ToMiniString {
    T evaluate(T x, T y, T z, Evaluator<T> evaluator);
}
