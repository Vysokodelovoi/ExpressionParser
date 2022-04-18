package expression.evaluators;

public interface Evaluator<T> {
    T evalMultiply(T first, T second);
    T evalSum(T first, T second);
    T evalDivision(T first, T second);
    T evalSubtract(T first, T second);
    T evalConst(int value);
    T evalMax(T first, T second);
    T evalMin(T first, T second);
    T evalAbs(T expr);
    T evalNegate(T expr);
    T evalCount(T expr);
}
