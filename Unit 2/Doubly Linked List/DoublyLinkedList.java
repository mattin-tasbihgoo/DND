@SuppressWarnings("rawtypes")
public class DoublyLinkedList {
	// Implements a circular doubly-linked list.
	private final ListNode2<Nucleotide> SENTINEL = new ListNode2<Nucleotide>(null);
	private int nodeCount;

	// Constructor: creates an empty list
	public DoublyLinkedList() {
		nodeCount = 0;
	}

	// Constructor: creates a list that contains
	// all elements from the array values, in the same order
	public DoublyLinkedList(Nucleotide[] values) {
		this();
		SENTINEL.setNext(SENTINEL);
		SENTINEL.setPrevious(SENTINEL);
		for (Nucleotide value : values) {
			add(value);
		}
	}

	public ListNode2<Nucleotide> getSentinel() {
		return SENTINEL;
	}

	public ListNode2<Nucleotide> getHead() {
		return SENTINEL.getNext();
	}

	public ListNode2<Nucleotide> getTail() {
		return SENTINEL.getPrevious();
	}

	// Returns true if this list is empty; otherwise returns false.
	public boolean isEmpty() {
		return nodeCount == 0;
	}

	// Returns the number of elements in this list.
	public int size() {
		return nodeCount;
	}

	// Returns true if this list contains an element equal to obj;
	// otherwise returns false.
	public boolean contains(Nucleotide obj) {
		ListNode2 temp = SENTINEL;
		for (int i = 0; i < nodeCount; i++) {
			if (temp.getValue().equals(obj))
				return true;
		}
		return false;
	}

	// Returns the index of the first element in equal to obj;
	// if not found, returns -1.
	public int indexOf(Nucleotide obj) {
		ListNode2 temp = SENTINEL;
		for (int i = 0; i < nodeCount; i++) {
			if (temp.getValue().equals(obj))
				return i;
		}
		return -1;
	}

	// Adds obj to this collection. Returns true if successful;
	// otherwise returns false.
	@SuppressWarnings("unchecked")
	public boolean add(Nucleotide obj) {
		ListNode2 temp = new ListNode2<>(obj, getTail(), getHead());
		if (nodeCount == 0)
			SENTINEL.setNext(temp);
		SENTINEL.setPrevious(temp);
		nodeCount++;
		return true;
	}

	// Removes the first element that is equal to obj, if any.
	// Returns true if successful; otherwise returns false.
	public boolean remove(Nucleotide obj) {
		ListNode2<Nucleotide> temp = SENTINEL.getNext();
		for (int i = 0; i < nodeCount; i++) {
			if (temp.getValue().equals(obj)) {
				temp.getPrevious().setNext(temp.getNext());
				temp.getNext().setPrevious(temp.getPrevious());
				nodeCount--;
				return true;
			}
			temp = temp.getNext();
		}
		return false;
	}

	// Returns the i-th element.
	public Nucleotide get(int i) {
		if (i < 0 || i >= nodeCount) {
			throw new IndexOutOfBoundsException();
		}
		ListNode2<Nucleotide> temp = SENTINEL.getNext();
		for (int j = 0; j < nodeCount; j++) {
			if (j == i) {
				return temp.getValue();
			}
			temp = temp.getNext();
		}
		return null;
	}

	// Replaces the i-th element with obj and returns the old value.
	public Nucleotide set(int i, Nucleotide obj) {
		if (i < 0 || i >= nodeCount) {
			throw new IndexOutOfBoundsException();
		}
		ListNode2<Nucleotide> temp = SENTINEL.getNext();
		for (int j = 0; j < nodeCount; j++) {
			if (j == i) {
				Nucleotide temp2 = temp.getValue();
				temp.setValue(obj);
				return temp2;
			}
			temp = temp.getNext();
		}
		return null;
	}

	// Inserts obj to become the i-th element. Increments the size
	// of the list by one.
	public void add(int i, Nucleotide obj) {
	}

	// Removes the i-th element and returns its value.
	// Decrements the size of the list by one.
	public Nucleotide remove(int i) {
		if (i < 0 || i >= nodeCount) {
			throw new IndexOutOfBoundsException();
		}
		ListNode2<Nucleotide> temp = SENTINEL.getNext();
		for (int j = 0; j < nodeCount; j++) {
			if (j == i) {
				temp.getPrevious().setNext(temp.getNext());
				temp.getNext().setPrevious(temp.getPrevious());
				nodeCount--;
				return temp.getValue();
			}
			temp = temp.getNext();
		}
		return null;
	}

	// Returns a string representation of this list exactly like that for
	// MyArrayList.
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		ListNode2<Nucleotide> temp = SENTINEL.getNext();
		while (temp != null) {
			sb.append((temp).getValue());
			if ((temp).getNext() != null) {
				sb.append(", ");
			}
			temp = (temp).getNext();
		}
		sb.append("]");
		return sb.toString();
	}

	// Like question 7 on the SinglyLinkedList test:
	// Add a "segment" (another list) onto the end of this list
	public void addSegmentToEnd(DoublyLinkedList seg) {

	}

	// Like question 8 on the SinglyLinkedList test:
	// You are to remove the next 16 nodes from the list, after the node nodeBefore.
	// (on the test these nodes were assumed to contain CCCCCCCCGGGGGGGG, but here
	// you do not need to assume or check for that)
	public void removeCCCCCCCCGGGGGGGG(ListNode2<Nucleotide> nodeBefore) {

	}

	// Like question 9 on the SinglyLinkedList test:
	// You are to find and delete the first instance of seg in the list.
	// If seg is not in the list, return false, otherwise return true.
	public boolean deleteSegment(DoublyLinkedList seg) {

	}

	// Like question 10 on the SinglyLinkedList test:
	// Delete the last three nodes in the list
	// If there are not enough nodes, return false
	public boolean deleteLastThree() {

	}

	// Like question 11 on the SinglyLinkedList test:
	// Replaces every node containing "A" with three nodes containing "T" "A" "C"
	public void replaceEveryAWithTAC() {

	}

}
