public class TesterBST {
    public static void main(String[] args) {
        MyBST<Integer> bst = new MyBST<>();

        bst.add(12);
        bst.add(13);
        bst.add(13);
        System.out.println(bst.toString());
    }
}
