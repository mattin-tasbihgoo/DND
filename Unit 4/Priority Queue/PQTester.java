public class PQTester {
    public static void main(String[] args) {
        HeapPQ<Integer> heap = new HeapPQ<>();
        heap.add(1);
        heap.removeMin();
        heap.add(2);
        heap.add(3);
        heap.add(4);
        heap.add(8);
        heap.add(7);
        heap.add(1);
        heap.add(10);
        System.out.println(heap.toString());
    }
}
