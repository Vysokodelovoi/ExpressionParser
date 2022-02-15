package MyTests;

import expression.exceptions.ExpressionParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParserTests {
    ExpressionParser parser = new ExpressionParser();
    @Test
    void testPlusExpressions() {
        parser.parse("x min y ))");
        validFull("((y min x)min x)", "(-(-((x + 30))) + (y * (y * -(-2147483648))))");
        validMini("-(\u000B-2147483648)", "- -2147483648");
        System.err.println(parser.parse("-(\u000B-2147483648)").evaluate(0, 0, 0));
        validMini("x / y / z", "x / y / z");
        validMini("x    **   20 +   y   /  x/   z    ", "x ** 20 + y / x / z");
    }

    void validFull(String task, String expected) {
        System.out.println("Testing "+task);
        Assertions.assertEquals(expected, parser.parse(task).toString());
    }
    void validMini(String task, String expected) {
        System.out.println("Testing "+task);
        Assertions.assertEquals(expected, parser.parse(task).toMiniString());
    }
}
