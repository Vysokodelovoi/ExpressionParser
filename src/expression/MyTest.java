package expression;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// Че хотим:
//  Попасть в Jane street
//  Реализовать по принципам SOLID
//      Объект сам должен понимать нужно его оборачивать в скобки или нет
//      Сделать evaluate более общим, добавить метод, который будет делать грязную работу за него
//  Избавиться от лишних копирований в StringBuilder (miniString, toString)
//      Кажется что легче сначала сделать SOLID, моя реализация - *****

class MyTest {
    @Test
    void testHash() {
        validHash(
                new Multiply(
                        new Const(3),
                        new Variable("x")
                ),
                new Add(
                        new Const(2),
                        new Variable("x")
                )
        );

    }
    void validHash(Expression e1, Expression e2) {
        System.err.println(e1.hashCode());
        System.err.println(e2.hashCode());
        Assertions.assertEquals(e1.equals(e2),e1.hashCode() == e2.hashCode());
    }
    void valid(MyExpression exp, int value, int ans) {
        Assertions.assertEquals(ans, exp.evaluate(value));
    }
    @Test
    void testEvaluate() {
        valid(
                new Add(new Multiply(new Const(3), new Const(2)),
                        new Variable("x")), 2, 8
        );
        valid(
                new Add(new Subtract(new Const(4), new Const(2)),
                        new Variable("x")), 2, 4
        );
    }
    @Test
    void testToString() {
        valid(new Subtract(
                new Multiply(
                        new Const(2),
                        new Variable("x")
                ),
                new Const(3)).toString(),
                "((2 * x) - 3)"
        );
    }
    @Test
    void testMiniExpression() {
        valid(new Multiply(
                    new Const(2),
                    new Divide(new Const(1),
                            new Const(10))
                ).toMiniString(),
                "2 * (1 / 1)"
        );
    }
    void valid(String expression, String str) {
        Assertions.assertEquals(str, expression);
    }
}