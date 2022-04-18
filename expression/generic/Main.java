package expression.generic;

public class Main {

    public static void main(String[] args) throws Exception {
        GenericTabulator genericTabulator = new GenericTabulator();
        Object[][][] table =
                genericTabulator.tabulate(args[0].substring(1), args[1], -2, 2, -2, 2, -2, 2);
        for (int x = -2; x <= 2; x++) {
            for (int y = -2; y <= 2; y++) {
                for (int z = -2; z <= 2; z++) {
                    System.out.printf("expr(%s, %s, %s) = %s\n", x, y, z, table[x + 2][y + 2][z + 2]);
                }
            }
        }

    }
}
