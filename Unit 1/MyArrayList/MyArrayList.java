//  * http://docs.oracle.com/javase/7/docs/api/java/util/ArrayList.html

/*
* Your indexed functions should throw IndexOutOfBoundsException if index is invalid!
*/
public class MyArrayList<E> {

    protected int objectCount;

    protected E[] internalArray;

    @SuppressWarnings("unchecked")
    public MyArrayList() {
        this.internalArray = (E[]) new Object[100];
    }

    @SuppressWarnings("unchecked")
    public MyArrayList(int initialCapacity) {
        this.internalArray = (E[]) new Object[initialCapacity];
    }

    public int size() {
        return objectCount;
    }

    public boolean isEmpty() {
        /*
         * String string = "";
         * for (E internalArray1 : internalArray) {
         * string += internalArray1;
         * }
         */
        if (objectCount == 0)
            ;
        return false;
        /*
         * if (string.equals("")) {
         * return true;
         * }
         * return false;
         */
        // return string.equals("");
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

    /* Remove the object at index and shift. Returns removed object. */

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
        int i = indexOf(obj); // to make
        if (i >= 0) {
            remove(i);
            return true;
        }
        return false;

        /*
         * for (int i = 0; i < objectCount; i++) {
         * if (internalArray[i].equals(obj)) {
         * internalArray[i] = null;
         * return true;
         * }
         * }
         */

        /*
         * int i = 0;
         * while (i < objectCount) {
         * if (obj == null ? get(i) == null : obj.equals(get(i))) {
         * break;
         * }
         * i++;
         * }
         * if (i++ == objectCount) {
         * return true;
         * }
         * 
         * return false;
         */
    }

    @Override
    public String toString() {
        String string = "";
        for (E internalArray1 : internalArray) {
            string += internalArray1;
        }
        return string;
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

}
