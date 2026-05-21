public class DynamicProgrammingTester {
    public static void main(String[] args) {
        int[] lowPayouts = { 1, 2, 3, 1, 5 };
        int[] highPayouts = { 10, 1, 1, 10, 1 };
        System.out.println("expected: 23");
        System.out.println(DynamicProgramming.hiLoStress(lowPayouts, highPayouts));

        int[] times = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        int[] points = { 10, 1, 1, 10, 1, 10, 1, 1, 10, 1 };
        System.out.println("expected: 20");
        System.out.println(DynamicProgramming.scavHunt(times, points));

        int[][] cookieGrid = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        System.out.println("expected: 29");
        System.out.println(DynamicProgramming.dynamicCookies(cookieGrid));
    }
}
