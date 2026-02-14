public class MyPQ<E extends Comparable<E>> implements MyPriorityQueue<E> {

    private E[] heap;
    private int objectCount;

    public MyPQ() {
        this.heap = (E[]) new Comparable[3];
        this.objectCount = 0;
    }

    // Returns the number of elements in the priority queue
    public int size() {
        return this.objectCount;
    }

    // DO NOT CHANGE MY JANKY TOSTRING!!!!!
    public String toString() {
        StringBuffer stringbuf = new StringBuffer("[");
        for (int i = 0; i < objectCount; i++) {
            stringbuf.append(heap[i]);
            if (i < objectCount - 1)
                stringbuf.append(", ");
        }
        stringbuf.append("]\nor alternatively,\n");

        for (int rowLength = 1, j = 0; j < objectCount; rowLength *= 2) {
            for (int i = 0; i < rowLength && j < objectCount; i++, j++) {
                stringbuf.append(heap[j] + " ");
            }
            stringbuf.append("\n");
            if (j < objectCount) {
                for (int i = 0; i < Math.min(objectCount - j, rowLength * 2); i++) {
                    if (i % 2 == 0)
                        stringbuf.append("/");
                    else
                        stringbuf.append("\\ ");
                }
                stringbuf.append("\n");
            }
        }
        return stringbuf.toString();
    }

    // Doubles the size of the heap array
    private void increaseCapacity() {
        E[] newHeap = (E[]) new Comparable[objectCount * 2];
        int counter = 0;
        for (E e : heap) {
            newHeap[counter] = e;
            counter++;
        }
        heap = newHeap;
    }

    // Returns the index of the "parent" of index i
    private int parent(int i) {
        return (i + 1) / 2 - 1;
    }

    // Returns the index of the *smaller child* of index i
    private int smallerChild(int i) {
        int left = i * 2 + 1;
        int right = i * 2 + 2;

        if (left >= objectCount)
            return -1;
        if (right >= objectCount)
            return left;
        return (heap[left].compareTo(heap[right]) <= 0) ? left : right;
    }

    // Swaps the contents of indices i and j
    private void swap(int i, int j) {
        E temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // Bubbles the element at index i upwards until the heap properties hold again.
    private void bubbleUp(int i) {
        for (int j = i; j > 0; j = parent(j)) {
            if (heap[j].compareTo(heap[parent(j)]) >= 0)
                break;
            swap(j, parent(j));
        }
    }
    // for (place; bubbleup; up parent);

    // Bubbles the element at index i downwards until the heap properties hold
    // again.
    private void bubbleDown(int i) {
        for (int j = smallerChild(i); j != -1 && heap[i].compareTo(heap[j]) > 0; i = j, j = smallerChild(i))
            swap(i, j);
    }

    @Override
    public void add(E obj) {
        if (obj == null)
            throw new NullPointerException();
        if (heap.length == objectCount)
            increaseCapacity();

        heap[objectCount] = obj;
        objectCount++;
        bubbleUp(objectCount - 1);
    }

    @Override
    public E removeMin() {
        E temp = heap[0];
        heap[0] = heap[objectCount - 1];
        heap[objectCount - 1] = null;

        objectCount--;
        bubbleDown(0);
        return temp;
    }

    @Override
    public E peek() {
        return heap[0];
    }

    @Override
    public boolean isEmpty() {
        return objectCount == 0;
    }
}
