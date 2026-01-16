public class TesterBST {
    public static void main(String[] args) {
        MyBST<Double> bst = new MyBST<>();
        bst.add(4.0);
        bst.add(2.0);
        bst.add(3.0);
        bst.add(2.5);
        bst.add(5.0);
        bst.add(6.0);
        bst.add(5.5);
        bst.add(3.5);
        bst.add(5.4);
        bst.add(6.5);
        bst.add(3.7);
        bst.add(5.1);
        bst.add(4.1);
        bst.add(3.1);

        System.out.println(bst);
        bst.remove(4.0);
        bst.remove(3.0);
        bst.remove(2.5);
        bst.remove(5.0);
        bst.remove(6.0);
        System.out.println(bst);
        MyBST<Integer> bst2 = new MyBST<>();
        for (int i = 0; i < 100; i++) {
            bst2.add(i);
        }
        bst2.remove(14);
        System.out.println(bst2.toString());
    }
}