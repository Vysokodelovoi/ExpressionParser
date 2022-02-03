package Parser;
import base.ExtendedRandom;
import expression.*;
import expression.parser.ExpressionParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

public class ParserTester {
    protected final ExtendedRandom random = new ExtendedRandom();

    private static final String LOOKBEHIND = "(?<![a-zA-Z0-9<>*/+-])";
    private static final String LOOKAHEAD = "(?![a-zA-Z0-9<>*/])";
    private static final Pattern SPACES = Pattern.compile(LOOKBEHIND + " | " + LOOKAHEAD + "|" + LOOKAHEAD + LOOKBEHIND);
    private String extraSpaces(final String expression) {
        return SPACES.matcher(expression).replaceAll(r -> random.randomString(ExtendedRandom.SPACES, random.nextInt(5)));
    }
    ExpressionParser parser = new ExpressionParser();
    @Test
    void testSpaces() {
        validToMiniString("- 0", "-(0)");
        validToMiniString("- 0", extraSpaces("-(0)"));
        validToMiniString("- 0", "- 0");
    }
    @Test
    void testUnaryMinus() {
        System.out.println(parser.parse("-(z)/2"));
        validToMiniString("-((- z / (z + y)))","-((-(z) / (z + y)))");
    }
    void validate(MyExpression expression) {
        System.out.println("Testing " + expression.toMiniString());
        Assertions.assertEquals(expression, parser.parse(expression.toString()), "Wrong Answer");
    }
    void validToMiniString(String expected, String task) {

        Assertions.assertEquals(expected, parser.parse(task).toMiniString(), task);
    }

    void validToString(String expected, String task) {
        Assertions.assertEquals(expected, parser.parse(task).toString(), task);
    }
}
