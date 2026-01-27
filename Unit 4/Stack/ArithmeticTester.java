public class ArithmeticTester {
    public static void main(String[] args) {
        String exp = "3 + 4 * 2 + ( 1 - 5 ) ^ 2 % 2";
        String stout = Arithmetic.convertClassicToStout(exp);
        int result = Arithmetic.evaluate(exp);

        System.out.println("Classic: " + exp);
        System.out.println("Stout:   " + stout);
        System.out.println("Result:  " + result);
    }
}
