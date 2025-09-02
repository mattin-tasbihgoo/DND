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

        System.out.println("Test 1");
        PugSaver.rescuePugs(test1);
        for (int i = 0; i < test1.size(); i++) {
            System.out.println(test1.get(i).toString());
        }

        @SuppressWarnings("Convert2Diamond")
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
    }
}
