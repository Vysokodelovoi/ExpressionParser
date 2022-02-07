package MyTests;

import expression.parser.ExpressionParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParserTests {
    ExpressionParser parser = new ExpressionParser();
//    @Test
//    void testUnaryMinus() {
//        validMini("-(-(- x + 1934591522 + - 856190260) / ((-1168772442 - -1079533465) / (-497368197 + -1673879928 - z)))",
//                "-(-(- x + 1934591522 + - 856190260) / ((-1168772442 - -1079533465) / (-497368197 + -1673879928 - z)))");
//    }
    @Test
    void testPlusExpressions() {

//        validMini(
//                "(-(-(z + - 114290907) + y - 1870609701 + -320929404 + y) - (z * 879169053 / -(1152301476 + -1890079430) - ((z + 1482195714) / x + 818433977 - z))) / (- -(-1188583829 / x * (x / 137880815) / (704803797 * -350057482 - -1760636676 * 1582506864)) + (-2009202626 - x) * y * (x / -1082178582) / (- 805001995 / - 1971718706))",
//                "(-(-(z + - 114290907) + y - 1870609701 + -320929404 + y) - (z * 879169053 / -(1152301476 + -1890079430) - ((z + 1482195714) / x + 818433977 - z))) / (- -(-1188583829 / x * (x / 137880815) / (704803797 * -350057482 - -1760636676 * 1582506864)) + (-2009202626 - x) * y * (x / -1082178582) / (- 805001995 / - 1971718706))"
//        );
//        validMini(
//                "- -(-1188583829 / x * (x / 137880815) / (704803797 * -350057482 - -1760636676 * 1582506864)) + (-2009202626 - x) * y * (x / -1082178582) / (- 805001995 / - 1971718706)",
//                "- -(-1188583829 / x * (x / 137880815) / (704803797 * -350057482 - -1760636676 * 1582506864)) + (-2009202626 - x) * y * (x / -1082178582) / (- 805001995 / - 1971718706)"
//        );
////        validMini("-(-(x + y) * z + 1) / (- -(-1+2) + 3)",
//                "-(-(x + y) * z + 1) / (- -(-1 + 2) + 3)");
        validMini("+ x", "+ x");
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
