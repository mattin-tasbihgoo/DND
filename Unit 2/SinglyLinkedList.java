// Implements a singly-linked list.

// import java.util.List;

public class SinglyLinkedList<E> {
	private ListNode<E> head;
	private ListNode<E> tail;
	private int nodeCount;

	// Constructor: creates an empty list
	public SinglyLinkedList() {
		this.head = null;
		this.tail = null;
		this.nodeCount = 0;
	}

	// Constructor: creates a list that contains
	// all elements from the array values, in the same order
	@SuppressWarnings("unchecked")
	public SinglyLinkedList(Object[] values) {
		for (int i = 0; i < values.length; i++) {
			add((E) values[i]);
		}
	}

	public ListNode<E> getHead() {
		return head;
	}

	public ListNode<E> getTail() {
		return tail;
	}

	// Returns true if this list is empty; otherwise returns false.
	public boolean isEmpty() {
	}

	// Returns the number of elements in this list.
	public int size() {
	}

	// Returns true if this list contains an element equal to obj;
	// otherwise returns false.
	public boolean contains(E obj) {
	}

	// Returns the index of the first element in equal to obj;
	// if not found, returns -1.
	public int indexOf(E obj) {
	}

	// Adds obj to this collection. Returns true if successful;
	// otherwise returns false.
	// figure out the head
	public boolean add(E obj) {
		if (nodeCount == 0) {
			head = new ListNode<E>(obj, tail);
			tail = head;
		} else {
			ListNode<E> node = new ListNode<E>(obj);
			tail.setNext(node);
			tail = node;
			nodeCount++;
		}
		return true;
	}

	// Removes the first element that is equal to obj, if any.
	// Returns true if successful; otherwise returns false.
	public boolean remove(E obj) {

	}

	// Returns the i-th element.
	public E get(int i) {
	}

	// Replaces the i-th element with obj and returns the old value.
	public E set(int i, Object obj) {
	}

	// Inserts obj to become the i-th element. Increments the size
	// of the list by one.
	public void add(int i, Object obj) {
	}

	// Removes the i-th element and returns its value.
	// Decrements the size of the list by one.
	public E remove(int i) {

	}

	// Returns a string representation of this list exactly like that for
	// MyArrayList.
	@SuppressWarnings("unchecked")
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		E temp = (E) getHead();
		sb.append(temp);
		while (((ListNode<E>) temp).getNext() != null) {
			sb.append(((ListNode<E>) temp).getNext());
			sb.append(",");
		}
		sb.append("]");
		return sb.toString();
	}

}
