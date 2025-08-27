import java.util.ArrayList;
// import java.util.Collections;
// import java.util.Objects;

public class PugSaver {

	public static ArrayList<Dog> rescuePugs(ArrayList<Dog> list) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).toString().contains("Golden")) {
				continue;
			} else {
				list.set(0, list.get(i));
				// list.remove(i - 1);
			}
		}
		return list;
	}
	/*
	 * int i = 0;
	 * int counter = 0;
	 * while (i + counter< list.size()){
	 * if (list.get(i).toString().contains("Golden")) {
	 * continue;
	 * } else {
	 * for (int j = list.size() ; j > counter; j--) {
	 * if (list.get(j).getBreed().toString().contains("Golden"));
	 * }
	 * }
	 * }
	 */
}
