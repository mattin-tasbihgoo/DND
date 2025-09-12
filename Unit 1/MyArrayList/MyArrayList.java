
import java.util.Arrays;

public class MyArrayList<E> {

    protected int objectCount;

    protected E[] internalArray;

    @SuppressWarnings("unchecked")
    // Big O: 1
    public MyArrayList() {
        this.internalArray = (E[]) new Object[100];
        this.objectCount = 0;
    }

    @SuppressWarnings("unchecked")
    // Big O: 1
    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0) throw new IllegalArgumentException();
        this.internalArray = (E[]) new Object[initialCapacity];
        this.objectCount = 0;
    }

    // Big O: 1
    public int size() {
        return objectCount;
    }

    // Big O: 1
    public boolean isEmpty() {
        return objectCount == 0;
    }

    // Big O: 1
    public E get(int index) {
        checkIndex(index);
        return internalArray[index];
    }

    // Big O: 1
    public E set(int index, E obj) {
        checkIndex(index);
        E temp = internalArray[index];
        internalArray[index] = obj;
        return temp;
    }

    // Big O: n
    public boolean contains(E obj) {
        return indexOf(obj) != -1;
    }

    // Big O: n
    public void add(int index, E obj) {
        if (index < 0 || index > objectCount) {
            throw new IndexOutOfBoundsException();
        }
        capCheck(objectCount + 1);
        int num = objectCount - index;
        if (num > 0) {
            System.arraycopy(internalArray, index, internalArray, index + 1, num);
        }
        internalArray[index] = obj;
        objectCount++;
    }

    // @SuppressWarnings("unchecked")
    // Big O: n
    public boolean add(E obj) {
        capCheck(objectCount + 1);
        internalArray[objectCount++] = obj;
        return true;
    }

    // Big O: n
    public E remove(int index) {
        checkIndex(index);
        E r = internalArray[index];
        int num = objectCount - index - 1;
        if (num > 0) {
            System.arraycopy(internalArray, index + 1, internalArray, index, num);
        }
        internalArray[--objectCount] = null;
        return r;
    }

    // Big O: n
    public boolean remove(E obj) {
        int i = indexOf(obj);
        if (i >= 0) {
            remove(i);
            return true;
        }
        return false;
    }

    // Big O: n
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < objectCount; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(String.valueOf(internalArray[i]));
        }
        sb.append("]");
        return sb.toString();
    }

    // Big O: n
    private int indexOf(E obj) {
        for (int i = 0; i < objectCount; i++) {
            if (obj == null ? internalArray[i] == null : obj.equals(internalArray[i])) {
                return i;
            }
        }
        return -1;
    }

    
    private void checkIndex(int index) {
        if (index < 0 || index >= objectCount) {
            throw new IndexOutOfBoundsException();
        }
    }

    // Big O: 1
    // Big O: n if resized
    private void capCheck(int min) {
        if (internalArray.length >= min) {
            return;
        }

        int cap = internalArray.length * 2;
        internalArray = Arrays.copyOf(internalArray, cap);
    }

}
