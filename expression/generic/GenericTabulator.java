package expression.generic;

import expression.*;
import expression.evaluators.*;
import expression.parser.ExpressionParser;

import java.util.Map;

import static expression.evaluators.NumberEvaluators.*;

public class GenericTabulator implements Tabulator {
    private static final Map<String, Evaluator<? extends Number>> EVALUATION_TYPES_BY_MODE = Map.of(
            // :NOTE: Экземпляры
            // *FIXED*
            "i", INT_EVAL,
            "d", DOUBLE_EVAL,
            "bi", BIGINT_EVAL,
            "u", UNCHECKED_INT_EVAL,
            "l", LONG_EVAL,
            "f", FLOAT_EVAL
    );
    @Override
    public Object[][][] tabulate(final String mode, final String expression, final int x1, final int x2, final int y1, final int y2, final int z1, final int z2) throws Exception {
        return buildTable(EVALUATION_TYPES_BY_MODE.get(mode), expression, x1, x2, y1, y2, z1, z2);
    }
    public <T> Object[][][] buildTable(final Evaluator<T> evaluator, final String expression, final int x1, final int x2, final int y1, final int y2, final int z1, final int z2) throws Exception {
        final MyExpression<T> expr = new ExpressionParser<T>().parse(expression);
        final Object[][][] result = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        for (int x = x1; x <= x2; x++) {
            for (int y = y1; y <= y2; y++) {
                for (int z = z1; z <= z2; z++) {
                    try {
                        result[x - x1][y - y1][z - z1] =
                                expr.evaluate(evaluator.evalConst(x), evaluator.evalConst(y), evaluator.evalConst(z), evaluator);
                        // :NOTE: catch (Exception e)
                        // *fixed*
                    } catch (final ArithmeticException skipped) {
                        // :NOTE: ???
                        // *было result[][][] = null*
                    }
                }
            }
        }
        return result;
    }
}
