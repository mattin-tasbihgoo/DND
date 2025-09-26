
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
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public SinglyLinkedList(Object[] values) {
        this();
        for (Object value : values) {
            add((E) value);
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
        return nodeCount == 0;
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
            if (temp.getValue().equals(obj)) {
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
        ListNode<E> temp = new ListNode<>(obj);
        if (head == null) {
            head = temp;
            tail = head;
        } else {
            tail.setNext(temp);
            tail = temp;
        }
        nodeCount++;
        return true;
    }

    // Removes the first element that is equal to obj, if any.
    // Returns true if successful; otherwise returns false.
    public boolean remove(E obj) {
        if (head == null) {
            return false;
        }
        ListNode<E> temp = head;

        if (head.getValue().equals(obj)) {
            head = head.getNext();
            nodeCount--;
            if (head == null) {
                tail = null;
            }
            return true;
        }

        while (temp.getNext() != null) {
            if (temp.getNext().getValue().equals(obj)) {
                if (temp.getNext() == tail) {
                    tail = temp;
                }
                temp.setNext(temp.getNext().getNext());
                nodeCount--;
                return true;
            }
            temp = temp.getNext();
        }
        return false;
    }

    // Returns the i-th element.
    public E get(int i) {
        if (i < 0 || i >= nodeCount) {
            throw new IndexOutOfBoundsException();
        }
        return forHelper(0, i).getValue();
    }

    // Replaces the i-th element with obj and returns the old value.
    public E set(int i, Object obj) {
        ListNode<E> temp = forHelper(0, i);
        E temp2 = temp.getValue();
        temp.setValue((E) obj);
        return temp2;
    }

    // Inserts obj to become the i-th element. Increments the size
    // of the list by one.
    public void add(int i, Object obj) {
        if (i < 0 || i > nodeCount) {
            throw new IndexOutOfBoundsException();
        }
        ListNode<E> temp = new ListNode<>((E) obj);
        if (i == 0) {
            temp.setNext(head);
            head = temp;
            if (nodeCount == 0) {
                tail = temp;
            }
        } else {
            ListNode<E> temp2 = forHelper(0, i - 1);
            temp.setNext(temp2.getNext());
            temp2.setNext(temp);
            if (temp.getNext() == null) {
                tail = temp;
            }
        }
        nodeCount++;
    }

    // Removes the i-th element and returns its value.
    // Decrements the size of the list by one.
    public E remove(int i) {
        if (i < 0 || i >= nodeCount) {
            throw new IndexOutOfBoundsException();
        }
        E temp = head.getValue();
        if (i == 0) {
            head = head.getNext();
            if (head == null) {
                tail = null;
            }
        } else {
            ListNode<E> temp2 = forHelper(0, i - 1);
            ListNode<E> temp3 = temp2.getNext();
            temp = temp3.getValue();
            temp2.setNext(temp3.getNext());
            if (temp3 == tail) {
                tail = temp2;
            }
        }

        nodeCount--;
        return (E) temp;
    }

    // Returns a string representation of this list exactly like that for
    // MyArrayList.
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        E temp = (E) getHead();
        while (temp != null) {
            sb.append(((ListNode<E>) temp).getValue());
            if (((ListNode<E>) temp).getNext() != null) {
                sb.append(", ");
            }
            temp = (E) ((ListNode<E>) temp).getNext();
        }
        sb.append("]");
        return sb.toString();
    }

    public ListNode<E> forHelper(int i, int n) {
        ListNode<E> temp = head;
        for (int j = 0; j < n; j++) {
            if (temp == null) {
                return null;
            }
            temp = temp.getNext();
        }
        return temp;
    }
}
