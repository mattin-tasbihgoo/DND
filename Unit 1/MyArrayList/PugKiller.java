
public class PugKiller {

    public static void main(String[] args) {
        Dog dog1 = new Dog("mattin", "Golden");
        Dog dog2 = new Dog("david", "Pug");
        Dog dog3 = new Dog("dylan", "");
        Dog dog4 = new Dog("felicia", "husky");
        Dog dog5 = new Dog("morgan", "Golden");
        @SuppressWarnings("Convert2Diamond")
        MyArrayList<Dog> test1 = new MyArrayList<Dog>();
        test1.add(0, dog1);
        test1.add(1, dog2);
        test1.add(2, dog3);
        test1.add(3, dog4);
        test1.add(4, dog5);

        long time = System.nanoTime();
        for (int i = 0; i < 200000000; i++) {
            test1.add(new Dog("mattin", "Golden"));
            test1.add(new Dog("david", "Pug"));
            test1.add(new Dog("dylan", ""));
            test1.add(new Dog("felicia", "husky"));
            test1.add(new Dog("morgan", "Golden"));
        }

        long time2 = System.nanoTime();

        System.out.printf("Build test1 (size %,d) took %.3f ms%n", test1.size(), (time2 - time) / 1_000_000.0);
        long time3 = System.nanoTime();
        PugSaver.rescuePugs(test1);
        long time4 = System.nanoTime();
        System.out.printf("Test 1: rescuePugs() took %.3f ms%n", (time4 - time3) / 1_000_000.0);

        /* @SuppressWarnings("Convert2Diamond")
        MyArrayList<Dog> test2 = new MyArrayList<Dog>();
        test2.add(0, dog4);
        test2.add(1, dog1);
        test2.add(2, dog3);
        test2.add(3, dog2);
        test2.add(4, dog5);

        System.out.println("Test 2");
        PugSaver.rescuePugs(test2);
        for (int i = 0; i < test2.size(); i++) {
            System.out.println(test2.get(i).toString());
        }

        @SuppressWarnings("Convert2Diamond")
        MyArrayList<Dog> test3 = new MyArrayList<Dog>();
        test3.add(0, dog2);
        test3.add(1, dog3);
        test3.add(2, dog1);
        test3.add(3, dog4);
        test3.add(4, dog5);

        System.out.println("Test 3");
        PugSaver.rescuePugs(test3);
        for (int i = 0; i < test3.size(); i++) {
            System.out.println(test3.get(i).toString());
        }

        System.out.println("Other tests");

        testAddIndexOnEmpty();
        testAddIndexShift();
        testAppendGrowth();
        testToStringAndNull();
        testIsEmpty();

    }

    private static void testAddIndexOnEmpty() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add(0, "A");
        assertEquals("after add(0,'A')", "[A]", list.toString());
    }

    private static void testAddIndexShift() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("A");
        list.add("C");
        list.add(1, "B");
        assertEquals("insert middle", "[A, B, C]", list.toString());
    }

    private static void testAppendGrowth() {
        MyArrayList<Integer> list = new MyArrayList<>(1);
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        assertEquals("size==10", 10, list.size());
        assertEquals("last==9", 9, list.get(9));
    }

    private static void testToStringAndNull() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add(null);
        list.add("X");
        assertEquals("toString with null", "[null, X]", list.toString());
    }

    private static void testIsEmpty() {
        MyArrayList<String> list = new MyArrayList<>();
        assertTrue("empty list isEmpty()", list.isEmpty());
        list.add("A");
        assertFalse("non-empty isEmpty()", list.isEmpty());
    }

    private static void assertEquals(String label, Object exp, Object act) {
        if ((exp == null && act == null) || (exp != null && exp.equals(act))) {
            System.out.println("[PASS] " + label + " -> " + exp);
        } else {
            System.out.println("[FAIL] " + label + " -> expected: " + exp + ", actual: " + act);
        }
    }

    private static void assertTrue(String label, boolean cond) {
        if (cond) {
            System.out.println("[PASS] " + label);
        } else {
            System.out.println("[FAIL] " + label + " -> expected true");
        }
    }

    private static void assertFalse(String label, boolean cond) {
        if (!cond) {
            System.out.println("[PASS] " + label);
        } else {
            System.out.println("[FAIL] " + label + " -> expected false");
        } */
    }
}
