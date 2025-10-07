// make this efficient with Big O

@SuppressWarnings("rawtypes")
public class DoublyLinkedList {
    // Implements a circular doubly-linked list.

    private final ListNode2<Nucleotide> SENTINEL = new ListNode2<>(null);
    private int nodeCount;

    // Constructor: creates an empty list
    public DoublyLinkedList() {
        nodeCount = 0;
        SENTINEL.setNext(SENTINEL);
        SENTINEL.setPrevious(SENTINEL);
    }

    // Constructor: creates a list that contains
    // all elements from the array values, in the same order
    public DoublyLinkedList(Nucleotide[] values) {
        this();
        SENTINEL.setNext(SENTINEL);
        SENTINEL.setPrevious(SENTINEL);
        for (Nucleotide value : values) {
            ListNode2<Nucleotide> temp = new ListNode2<>(value, SENTINEL.getPrevious(), SENTINEL);
            SENTINEL.getPrevious().setNext(temp);
            SENTINEL.setPrevious(temp);
            nodeCount++;
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
        ListNode2 temp = SENTINEL.getNext();
        while (temp != SENTINEL) {
            if (temp.getValue().equals(obj)) {
                return true;
            }
            temp = temp.getNext();
        }
        return false;
    }

    // Returns the index of the first element in equal to obj;
    // if not found, returns -1.
    public int indexOf(Nucleotide obj) {
        ListNode2<Nucleotide> temp = SENTINEL.getNext();
        int i = 0;
        while (temp != SENTINEL) {
            if (temp.getValue().equals(obj)) {
                return i;
            }
            temp = temp.getNext();
            i++;
        }
        return -1;
    }

    // Adds obj to this collection. Returns true if successful;
    // otherwise returns false.
    @SuppressWarnings("unchecked")
    public boolean add(Nucleotide obj) {
        ListNode2 temp = new ListNode2<>(obj, SENTINEL.getPrevious(), SENTINEL);
        if (nodeCount == 0) {
            SENTINEL.setNext(temp);
        }
        SENTINEL.getPrevious().setNext(temp);
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
        indexCheck(i);
        ListNode2<Nucleotide> temp = SENTINEL.getNext();
        for (int j = 0; j < i; j++) {
            temp = temp.getNext();
        }
        return temp.getValue();
    }

    // Replaces the i-th element with obj and returns the old value.
    public Nucleotide set(int i, Nucleotide obj) {
        indexCheck(i);
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
        if (i < 0 || i > nodeCount)
            throw new IndexOutOfBoundsException();
        ListNode2<Nucleotide> temp = SENTINEL;
        for (int j = 0; j < i; j++)
            temp = temp.getNext();
        // temp is the node before the new one
        // temp2 is the new node
        ListNode2<Nucleotide> temp2 = new ListNode2<>(obj, temp, temp.getNext());
        temp.getNext().setPrevious(temp2);
        temp.setNext(temp2);
        nodeCount++;
    }

    // Removes the i-th element and returns its value.
    // Decrements the size of the list by one.
    public Nucleotide remove(int i) {
        indexCheck(i);
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
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        ListNode2<Nucleotide> temp = SENTINEL.getNext();
        if (temp != SENTINEL) { // in case there's nothing
            sb.append(temp.getValue());
            temp = temp.getNext();
        }
        while (temp != SENTINEL) {
            sb.append(", ");
            sb.append(temp.getValue());
            temp = temp.getNext();
        }
        sb.append("]");
        return sb.toString();
    }

    // Like question 7 on the SinglyLinkedList test:
    // Add a "segment" (another list) onto the end of this list
    public void addSegmentToEnd(DoublyLinkedList seg) {
        if (seg == null || seg.isEmpty()) {
            return;
        }
        getTail().setNext(seg.getHead());
        seg.getHead().setPrevious(getTail());
        seg.getTail().setNext(SENTINEL);
        SENTINEL.setPrevious(seg.getTail());

        nodeCount += seg.size();
    }

    // Like question 8 on the SinglyLinkedList test:
    // You are to remove the next 16 nodes from the list, after the node nodeBefore.
    // (on the test these nodes were assumed to contain CCCCCCCCGGGGGGGG, but here
    // you do not need to assume or check for that)
    public void removeCCCCCCCCGGGGGGGG(ListNode2<Nucleotide> nodeBefore) {
        if (nodeBefore == null) {
            return;
        }

        ListNode2<Nucleotide> temp = nodeBefore.getNext();
        for (int i = 0; i < 16 && temp != SENTINEL; i++) {
            temp = temp.getNext();
            nodeCount--;
        }

        nodeBefore.setNext(temp);
        temp.setPrevious(nodeBefore);
    }

    // Like question 9 on the SinglyLinkedList test:
    // You are to find and delete the first instance of seg in the list.
    // If seg is not in the list, return false, otherwise return true.
    public boolean deleteSegment(DoublyLinkedList seg) {
        if (seg == null || seg.isEmpty())
            return false;

        ListNode2<Nucleotide> segHead = seg.getHead();
        if (segHead == null || segHead == seg.getSentinel())
            return false;

        ListNode2<Nucleotide> current = SENTINEL.getNext();
        while (current != SENTINEL) {
            ListNode2<Nucleotide> segCurr = segHead;
            ListNode2<Nucleotide> listCurr = current;
            boolean match = true;

            for (int i = 0; i < seg.size() && listCurr != SENTINEL; i++) {
                if (segCurr == null || listCurr == null || segCurr.getValue() == null || listCurr.getValue() == null ||
                        !listCurr.getValue().equals(segCurr.getValue())) {
                    match = false;
                    break;
                }
                listCurr = listCurr.getNext();
                segCurr = segCurr.getNext();
            }

            if (match && segCurr == seg.getSentinel()) {
                ListNode2<Nucleotide> nodeAfter = current;
                for (int i = 0; i < seg.size(); i++) {
                    nodeAfter = nodeAfter.getNext();
                }

                if (current.getPrevious() != null && nodeAfter != null) {
                    current.getPrevious().setNext(nodeAfter);
                    nodeAfter.setPrevious(current.getPrevious());
                    nodeCount -= seg.size();
                    return true;
                }
            }

            current = current.getNext();
        }
        return false;
    }

    // Like question 10 on the SinglyLinkedList test:
    // Delete the last three nodes in the list
    // If there are 8not enough nodes, return false
    public boolean deleteLastThree() {
        if (nodeCount < 3) {
            return false;
        }

        // temp = node to remove
        // temp2 = previous
        ListNode2<Nucleotide> temp = getTail();
        for (int i = 0; i < 3; i++) {
            ListNode2<Nucleotide> temp2 = temp.getPrevious();
            temp2.setNext(temp.getNext());
            temp.getNext().setPrevious(temp2);
            temp = temp2;
            nodeCount--;
        }

        return true;
    }

    // Like question 11 on the SinglyLinkedList test:
    // Replaces every node containing "A" with three nodes containing "T" "A" "C"
    public void replaceEveryAWithTAC() {
        ListNode2<Nucleotide> curr = SENTINEL.getNext();
        while (curr != SENTINEL) {
            if (curr.getValue().toString().equals("A")) {
                ListNode2<Nucleotide> t = new ListNode2<>(Nucleotide.T);
                ListNode2<Nucleotide> a = new ListNode2<>(Nucleotide.A);
                ListNode2<Nucleotide> c = new ListNode2<>(Nucleotide.C);
                t.setNext(a);
                a.setPrevious(t);
                a.setNext(c);
                c.setPrevious(a);
                t.setPrevious(curr.getPrevious());
                curr.getPrevious().setNext(t);
                c.setNext(curr.getNext());
                curr.getNext().setPrevious(c);
                nodeCount += 2;
                curr = c.getNext();
            } else {
                curr = curr.getNext();
            }
        }
    }

    public void indexCheck(int i) {
        if (i < 0 || i >= nodeCount)
            throw new IndexOutOfBoundsException();
    }

}
