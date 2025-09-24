// Implements a singly-linked list.

// import java.util.List;
@SuppressWarnings("unchecked")
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
		if (nodeCount == 0)
			;
		return false;
	}

	// Returns the number of elements in this list.
	public int size() {
		return nodeCount;
	}

	// Returns true if this list contains an element equal to obj;
	// otherwise returns false.
	public boolean contains(E obj) {
		ListNode<E> temp = head;
		for (int i = 0; i < nodeCount; i++) {
			if (temp.getNext().equals(obj)) {
				return true;
			}
			temp = temp.getNext();
		}
		return false;
	}

	// Returns the index of the first element in equal to obj;
	// if not found, returns -1.
	public int indexOf(E obj) {
		ListNode<E> temp = head;
		for (int i = 0; i < nodeCount; i++) {
			if (temp.getValue().equals(obj)) {
				return i;
			}
			temp = temp.getNext();
		}
		return -1;
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
		int i = indexOf(obj);
		ListNode<E> temp = forHelper(0, i - 1);
		ListNode<E> temp2 = temp.getNext();
		temp2 = temp2.getNext();
		temp.setNext(temp2);
		nodeCount--;
		return true;
	}

	// Returns the i-th element.

	public E get(int i) {
		return (E) forHelper(0, i).getValue();
	}

	// Replaces the i-th element with obj and returns the old value.
	public E set(int i, Object obj) {

	}

	// Inserts obj to become the i-th element. Increments the size
	// of the list by one.
	public void add(int i, Object obj) {
		if (i == 0) {
			head = new ListNode<E>((E) obj, tail);
			tail = head;
		} else {
			ListNode<E> node = new ListNode<E>((E) obj);
			tail.setNext(node);
			tail = node;
			nodeCount++;
		}
	}

	// Removes the i-th element and returns its value.
	// Decrements the size of the list by one.
	public E remove(int i) {
		ListNode<E> temp = forHelper(0, i - 1);
		ListNode<E> temp2 = temp.getNext();
		ListNode<E> temp3 = temp2.getNext();
		temp.setNext(temp3);
		nodeCount--;
		return (E) temp2;
	}

	// Returns a string representation of this list exactly like that for
	// MyArrayList.
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

	public ListNode<E> forHelper(int i, int n) {
		ListNode<E> temp = head;
		for (int j = 0; j < i - 1; j++) {
			temp = temp.getNext();
		}
		return temp;
	}
}
