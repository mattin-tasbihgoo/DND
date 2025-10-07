// make this efficient with Big O

@SuppressWarnings("rawtypes")
public class DoublyLinkedList {
    // Implements a circular doubly-linked list.

    private final ListNode2<Nucleotide> SENTINEL = new ListNode2<>(null);
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
        ListNode2 temp = SENTINEL;
        for (int i = 0; i < nodeCount; i++) {
            if (temp.getValue().equals(obj)) {
                return true;
            }
        }
        return false;
    }

    // Returns the index of the first element in equal to obj;
    // if not found, returns -1.
    public int indexOf(Nucleotide obj) {
        ListNode2 temp = SENTINEL;
        for (int i = 0; i < nodeCount; i++) {
            if (temp.getValue().equals(obj)) {
                return i;
            }
        }
        return -1;
    }

    // Adds obj to this collection. Returns true if successful;
    // otherwise returns false.
    @SuppressWarnings("unchecked")
    public boolean add(Nucleotide obj) {
        ListNode2 temp = new ListNode2<>(obj, getTail(), getHead());
        if (nodeCount == 0) {
            SENTINEL.setNext(temp);
        }
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
    @Override
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
        if (seg == null || seg.isEmpty()) {
            return false;
        }

        ListNode2<Nucleotide> current = SENTINEL.getNext();
        while (current != SENTINEL) {
            ListNode2<Nucleotide> segCurr = seg.getHead();
            ListNode2<Nucleotide> listCurr = current;
            boolean match = true;

            for (int i = 0; i < seg.size() && listCurr != SENTINEL; i++) {
                if (!listCurr.getValue().equals(segCurr.getValue())) {
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

                current.getPrevious().setNext(nodeAfter);
                nodeAfter.setPrevious(current.getPrevious());
                nodeCount -= seg.size();
                return true;
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
        ListNode2<Nucleotide> current = SENTINEL.getNext();
        while (current != SENTINEL) {
            if (current.getValue().toString().equals("A")) {
                ListNode2<Nucleotide> tempT = new ListNode2<>(current.getValue());
                ListNode2<Nucleotide> tempA = new ListNode2<>(current.getValue());
                ListNode2<Nucleotide> tempC = new ListNode2<>(current.getValue());
                tempT.setNext(tempA);
                tempA.setPrevious(tempT);
                tempA.setNext(tempC);
                tempC.setPrevious(tempA);
                tempT.setPrevious(current.getPrevious());
                current.getPrevious().setNext(tempT);
                tempC.setNext(current.getNext());
                current.getNext().setPrevious(tempC);
                nodeCount += 2;
                current = tempC.getNext();
            } else {
                current = current.getNext();
            }
        }
    }

}
