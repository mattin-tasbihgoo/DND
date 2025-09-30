
public class SinglyLinkedListTester {

    public static void main(String[] args) {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        System.out.println(list.getHead().getValue()); // A
        System.out.println(list.getTail().getValue()); // C
        System.out.println(list); // [A,B,C]
        list.add(1, "X");
        System.out.println(list); // [A,X,B,C]
        list.remove(2);
        System.out.println(list); // [A,X,C]
        System.out.println(list.get(1)); // X
        list.set(1, "Y");
        System.out.println(list); // [A,Y,C]
        System.out.println(list.isEmpty()); // false
        System.out.println(list.size()); // 3
        list.remove("A");
        System.out.println(list); // [Y,C]
        System.out.println(list.indexOf("C")); // 1
        System.out.println(list.indexOf("Z")); // -1
        System.out.println(list.contains("Y")); // true
        System.out.println(list.contains("A")); // false


        list = new SinglyLinkedList<>();
        System.out.println(list.isEmpty()); // true
        System.out.println(list.size()); // 0
        System.out.println(list); // []
        list.add("M");
        list.add("N");
        list.add("O");
        System.out.println(list); // [M,N,O]
        System.out.println(list.getHead().getValue()); // M
        System.out.println(list.getTail().getValue()); // O
        list.remove(0);
        System.out.println(list); // [N,O]
        list.remove(list.size() - 1);
        System.out.println(list); // [N]
        list.add("P");
        System.out.println(list); // [N,P]
        list.add(0, "Q");
        System.out.println(list); // [Q,N,P]
        list.set(2, "R");
        System.out.println(list); // [Q,N,R]
        System.out.println(list.get(2)); // R
        list.add(1, "S");
        System.out.println(list); // [Q,S,N,R]
        list.remove("S");
        System.out.println(list); // [Q,N,R]

        list = new SinglyLinkedList<>();
        System.out.println(list); // []
        System.out.println(list.isEmpty()); // true
        list.add("T");
        System.out.println(list.getHead().getValue()); // T
        System.out.println(list.getTail().getValue()); // T
        System.out.println(list.size()); // 1
        list.remove(0);
        System.out.println(list); // []
        System.out.println(list.size()); // 0
    }
}
