public class RecursionTester {
    public static void main(String[] args) {
        ListNode third = new ListNode("object");
        ListNode second = new ListNode("whaddup", third);
        ListNode head = new ListNode("head", second);

        String[][] grid = new String[][] { { "a", "a", "a" }, { "a", "a", "a" }, { "a", "a", "a" } };
        gridFucntion(grid);
        Recursion.infect(grid, 0, 0);
        System.out.println();
        gridFucntion(grid);
    }

    private static void gridFucntion(String[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
