package Parser;
import expression.Const;
import expression.MyExpression;
import expression.Subtract;
import expression.Variable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParserTester {
    ExpressionParser parser = new ExpressionParser();
    @Test
    void testParser() {
        validate(
                new Subtract(new Variable("x"), new Const(2))
                );
        validate(
                new Subtract(
                    new Subtract(new Variable("x"), new Variable("y")),
                            new Variable("z")
                )
        );
    }
    void validate(MyExpression expression) {
        System.out.println("Testing " + expression.toMiniString());
        Assertions.assertEquals(expression, parser.parse(expression.toMiniString()), "Wrong Answer");
    }
}
