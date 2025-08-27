import java.util.ArrayList;
import java.util.Collections;
// import java.util.Objects;

public class PugSaver {

	public static ArrayList<Dog> rescuePugs(ArrayList<Dog> list) {
		int counter = 0;
		for (int i = 0; i < list.size(); i++) {
			if (!list.get(i).toString().contains("Golden")) {
				if (counter != i) {
					Collections.swap(list, counter, i);
				}
				counter++;
			}
		}
		return list;
	}
}
