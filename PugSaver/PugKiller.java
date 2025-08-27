import java.util.ArrayList;
import java.util.Arrays;

public class PugKiller {
    public static void main(String[] args) {
        Dog dog1 = new Dog("mattin", "Golden");
        Dog dog2 = new Dog("david", "Pug");
        Dog dog3 = new Dog("dylan", "");
        Dog dog4 = new Dog("felicia", "husky");
        Dog dog5 = new Dog("morgan", "Golden");
        ArrayList<Dog> goodDogs = new ArrayList<Dog>(Arrays.asList(dog1, dog2, dog3, dog4, dog5));
        PugSaver.rescuePugs(goodDogs);
        for (int i = 0; i < goodDogs.size(); i++) {
            System.out.println(goodDogs.get(i).toString());
        }
    }
}
