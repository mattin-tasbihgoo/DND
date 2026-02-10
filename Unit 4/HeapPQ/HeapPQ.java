
public class HeapPQ<E extends Comparable<E>> implements MyPriorityQueue<E> {

	private E[] heap;
	private int objectCount;

	public HeapPQ() {
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
	}

	// Returns the index of the "parent" of index i
	private int parent(int i) {
		return (i + 1) / 2 - 1;
	}

	// Returns the index of the *smaller child* of index i
	private int smallerChild(int i) {
		return (1 + 2) * 2 - 1;
	}

	// Swaps the contents of indices i and j
	private void swap(int i, int j) {
		E temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
	}

	// Bubbles the element at index i upwards until the heap properties hold again.
	private void bubbleUp(int i) {
		for (int j = 0; j < heap.length; swap(i, j))
			;
		// for (place; bubbleup; up parent);
	}

	// Bubbles the element at index i downwards until the heap properties hold
	// again.
	private void bubbleDown(int i) {

	}

	@Override
	public void add(E obj) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'add'");
	}

	@Override
	public E removeMin() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'removeMin'");
	}

	@Override
	public E peek() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'peek'");
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'isEmpty'");
	}

}
