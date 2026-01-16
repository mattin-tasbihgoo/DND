import java.util.EmptyStackException;

public class MyStack<E> {
    private final ListNode2<E> SENTINEL = new ListNode2<>(null);
    private int nodeCount = 0;

    public MyStack() {
        SENTINEL.setNext(SENTINEL);
        SENTINEL.setPrevious(SENTINEL);
    }

    public int size() {
        return nodeCount;
    }

    public boolean empty() {
        return nodeCount == 0;
    }

    public void push(E x) {
        ListNode2<E> tail = SENTINEL.getPrevious(), temp = new ListNode2<>(x, tail, SENTINEL);

        tail.setNext(temp);
        SENTINEL.setPrevious(temp);

        nodeCount++;
    }

    public E peek() {
        if (empty())
            throw new EmptyStackException();
        return SENTINEL.getPrevious().getValue();
    }

    public E pop() {
        if (empty())
            throw new EmptyStackException();

        ListNode2<E> tail = SENTINEL.getPrevious(), newTail = tail.getPrevious();

        newTail.setNext(SENTINEL);
        SENTINEL.setPrevious(newTail);

        nodeCount--;
        return tail.getValue();
    }
}
