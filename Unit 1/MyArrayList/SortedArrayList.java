
import java.util.NoSuchElementException;

public class SortedArrayList<E extends Comparable<E>> extends MyArrayList<E> {

	@Override
	public boolean contains(E obj) {
		if (obj == null) return false;
		return binarySearch(obj) >= 0;
	}

	// May not contain more than one of the same object
	@Override
    public boolean add(E obj) {
        if (obj == null) throw new IllegalArgumentException();

        int temp1 = binarySearch(obj);
        if (temp1 >= 0) return false;

        int temp2 = -temp1 - 1;
        super.add(temp2, obj);
        return true;
    }

	@Override
   public boolean remove(E obj) {
        if (obj == null) return false;

        int i = binarySearch(obj);
        if (i < 0) return false;

        super.remove(i);
        return true;
    }

	public E min() {
		if (this.isEmpty()) throw new NoSuchElementException();
		return this.get(0);
	}

	public E max() {
		if (this.isEmpty()) throw new NoSuchElementException();
		return this.get(this.size() - 1);
	}

	public int binarySearch(E obj) {
		if (obj == null) return -1;
		return binarySearchRecursiveHelper(obj, 0, objectCount - 1);
	}

	public int binarySearchRecursiveHelper(E obj, int low, int high) {
		if (low > high) return -(low + 1);

		int mid = low (high - low) / 2;

		int compare = this.get(mid).compareTo(obj);
		if (compare == 0) return mid;
		if (compare > 0) return binarySearchRecursiveHelper(obj, low, mid - 1);
		return binarySearchRecursiveHelper(obj, mid + 1, high);
	}
}
