
import java.util.ArrayList;

//big ): n
public class PugSaver {

	public static MyArrayList<Dog> rescuePugs(MyArrayList<Dog> list) {
		int counter = 0;
		for (int i = 0; i < list.size(); i++) {
			if (!list.get(i).toString().contains("Golden")) {
				if (counter != i) {
					Dog tmpDog = list.get(counter);
					list.set(counter, list.get(i));
					list.set(i, tmpDog);
				}
				counter++;
			}
		}
		return list;
	}

	public static ArrayList<Dog> rescuePugs(ArrayList<Dog> list) {
		int counter = 0;
		for (int i = 0; i < list.size(); i++) {
			if (!list.get(i).toString().contains("Golden")) {
				if (counter != i) {
					Dog tmpDog = list.get(counter);
					list.set(counter, list.get(i));
					list.set(i, tmpDog);
				}
				counter++;
			}
		}
		return list;
	}
}
