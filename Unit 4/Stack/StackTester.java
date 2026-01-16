public class StackTester {
    public static void main(String[] args) {
        MyStack<Integer> s = new MyStack<>();
        s.push(1);
        s.push(2);
        System.out.println(s.peek());
        s.pop();
        System.out.println(s.peek());
    }
}
