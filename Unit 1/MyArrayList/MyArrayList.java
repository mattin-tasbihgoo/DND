import java.util.Arrays;

public class MyArrayList<E> {

    protected int objectCount;

    protected E[] internalArray;

    @SuppressWarnings("unchecked")
    public MyArrayList() {
        this.internalArray = (E[]) new Object[5];
        this.objectCount = 0;
    }

    @SuppressWarnings("unchecked")
    public MyArrayList(int initialCapacity) {
        this.internalArray = (E[]) new Object[initialCapacity];
        this.objectCount = 0;
    }

    public int size() {
        return objectCount;
    }

    public boolean isEmpty() {
        return objectCount == 0;
    }

    public E get(int index) {
        checkIndex(index);
        return internalArray[index];
    }

    public E set(int index, E obj) {
        checkIndex(index);
        E temp = internalArray[index];
        internalArray[index] = obj;
        return temp;
    }

    public boolean contains(E obj) {
        for (E internalArray1 : internalArray) {
            if (internalArray1.equals(obj))
                ;
        }
        return false;
    }

    public void add(int index, E obj) {
        checkIndex(index);
        capCheck(objectCount + 1);
        internalArray[index] = obj;
        int num = objectCount - index;
        if (num > 0) {
            System.arraycopy(internalArray, index, internalArray, index + 1, num);
        }
        internalArray[index] = obj;
        objectCount++;
    }

    // @SuppressWarnings("unchecked")
    public boolean add(E obj) {
        capCheck(objectCount + 1);
        internalArray[objectCount++ ] = obj;
        return true;
    }

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

    public boolean remove(E obj) {
        int i = indexOf(obj);
        if (i >= 0) {
            remove(i);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < objectCount; i++) {
            if (i > 0) sb.append(", ");
            sb.append(internalArray[i].toString());
        }
        sb.append("]");
        return sb.toString();
    }

    private int indexOf(E obj) {
        for (int i = 0; i < objectCount; i++) {
            if (obj == null ? internalArray[i] == null : obj.equals(internalArray[i])) {
                return i;
            }
        }
        return -1;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > objectCount) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void capCheck(int min) {
        if (internalArray.length < min) {
            int cap = Math.max(internalArray.length, min);
            internalArray = Arrays.copyOf(internalArray, cap);
        }
    }

}
