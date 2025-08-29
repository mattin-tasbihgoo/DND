import java.util.ArrayList;
import java.util.Arrays;

public class PugKiller {
    public static void main(String[] args) {
        Dog dog1 = new Dog("mattin", "Golden");
        Dog dog2 = new Dog("david", "Pug");
        Dog dog3 = new Dog("dylan", "");
        Dog dog4 = new Dog("felicia", "husky");
        Dog dog5 = new Dog("morgan", "Golden");
        ArrayList<Dog> test1 = new ArrayList<Dog>(Arrays.asList(dog1, dog2, dog3, dog4, dog5));

        System.out.println("Test 1");
        PugSaver.rescuePugs(test1);
        for (int i = 0; i < test1.size(); i++) {
            System.out.println(test1.get(i).toString());
        }

        ArrayList<Dog> test2 = new ArrayList<Dog>(Arrays.asList(dog1, dog5, dog3, dog4, dog2));

        System.out.println("Test 2");
        PugSaver.rescuePugs(test2);
        for (int i = 0; i < test2.size(); i++) {
            System.out.println(test2.get(i).toString());
        }

        ArrayList<Dog> test3 = new ArrayList<Dog>(Arrays.asList(dog2, dog4, dog3, dog5, dog1));

        System.out.println("Test 3");
        PugSaver.rescuePugs(test3);
        for (int i = 0; i < test3.size(); i++) {
            System.out.println(test3.get(i).toString());
        }
    }
}
