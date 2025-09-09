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
        if (objectCount == 0)
            ;
        return false;
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
        doubleArray();
        internalArray[index] = obj;
        if (index >= objectCount) {
            objectCount = index + 1;
        }
    }

    // @SuppressWarnings("unchecked")
    public boolean add(E obj) {
        internalArray[objectCount] = obj;
        objectCount++;
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
            sb.append(", ");
            sb.append(internalArray[i].toString());
        }
        sb.append("]");
        return sb.toString();
    }

    private int indexOf(E obj) {
        for (int i = 0; i < objectCount; i++) {
            if (obj == null ? internalArray[i] == null : obj.equals(internalArray[i]))
                ;
        }
        return -1;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= objectCount) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void doubleArray() {
        if (objectCount == internalArray.length) {
            objectCount = objectCount * 2;
            internalArray = Arrays.copyOf(internalArray, objectCount);
        }
    }

}
