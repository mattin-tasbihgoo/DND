public class SortedArrayListTester {
    public static void main(String[] args) {
        SortedArrayList<Integer> list = new SortedArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(7);
        list.add(6);

        System.out.println("After adds: " + list);

        System.out.println("Add duplicate 4? " + list.add(4)); 
        System.out.println("After dup add: " + list);

        System.out.println("Contains 6? " + list.contains(6)); 
        System.out.println("Contains 99? " + list.contains(99)); 
        System.out.println("Index of 6: " + list.binarySearch(6)); 
        System.out.println("Index of 99: " + list.binarySearch(99)); 

        System.out.println("Min: " + list.min());
        System.out.println("Max: " + list.max());

        System.out.println("Remove 1? " + list.remove((Integer) 1)); 
        System.out.println("Remove 6? " + list.remove((Integer) 6)); 
        System.out.println("Remove 99? " + list.remove((Integer) 99)); 
        System.out.println("After removes: " + list); 

        while (!list.isEmpty()) {
            list.remove(0);
        }
        System.out.println("After clearing: " + list + " isEmpty=" + list.isEmpty());
    }
}
