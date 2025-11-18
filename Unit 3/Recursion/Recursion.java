
public class Recursion {

	// Prints the value of every node in the singly linked list with the given head,
	// but in reverse
	public static void printListInReverse(ListNode head) {
		if (head == null) {
			return;
		}
		printListInReverse(head.getNext());
		System.out.println(head.getValue());
	}

	// For the given 2D array of Strings, replaces the String at index[r][c]
	// with "infected" unless the String is "vaccinated";
	// also, any Strings they are orthogonally adjacent to
	// that are not "vaccinated" will also be infected, and any adjacent to
	// them as well etc.
	// Infecting someone who is already infected has no effect
	// Trying to infect outside the confines of the grid also has no effect
	// Precondition: grid has no null entries
	public static void infect(String[][] grid, int r, int c) {
		if (grid == null || grid.length == 0 || r < 0 || c < 0 || r >= grid.length || c >= grid[r].length)
			return;
		String cell = grid[r][c];
		if ("vaccinated".equals(cell) || "infected".equals(cell))
			return;
		grid[r][c] = "infected";
		infect(grid, r + 1, c);
		infect(grid, r - 1, c);
		infect(grid, r, c + 1);
		infect(grid, r, c - 1);
	}

	// How many subsets are there of the numbers 1...n
	// that don't contain any consecutive integers?
	// e.g. for n = 4, the subsets are {}, {1}, {2}, {3}, {4},
	// {1, 3}, {1, 4}, {2, 4}
	// The other subsets of 1,2,3,4 that DO contain consecutive integers are
	// {1,2}, {2,3}, {3,4}, {1,2,3}, {1,2,4}, {1,3,4}, {1,2,3,4}
	// Precondition: n > 0
	public static long countNonConsecutiveSubsets(int n) {
		long num = 0;
		if (n < 0)
			return 0;

		if (n == 0)
			return 1;
		if (n <= 2)
			return n + 1;

		num = countNonConsecutiveSubsets(n - 1) + countNonConsecutiveSubsets(n - 2);

		return num;
	}

	// A kid at the bottom of the stairs can jump up 1, 2, or 3 stairs at a time.
	// How many different ways can they jump up n stairs?
	// Jumping 1-1-2 is considered different than jumping 1-2-1
	// Precondition: n > 0
	public static long countWaysToJumpUpStairs(int n) {
		/*
		 * switch (n) { // vs code told me to do this
		 * case 1 -> {
		 * return 1;
		 * }
		 * case 2 -> {
		 * return 2;
		 * }
		 * case 3 -> {
		 * return 4;
		 * }
		 * default -> {
		 * }
		 * }
		 */

		if (n == 1) {
			return 1;
		} else if (n == 2) {
			return 2;
		} else if (n == 3) {
			return 4;
		}
		return countWaysToJumpUpStairs(n - 1) + countWaysToJumpUpStairs(n - 2) + countWaysToJumpUpStairs(n - 3);
	}

	// Everything above this line does NOT require a recursive helper method
	// ----------------------------------
	// Everything below this line requires a recursive helper method
	// Any recursive helper method you write MUST have a comment describing:
	// 1) what the helper method does/returns
	// 2) your description must include role of each parameter in the helper method

	// Prints all the subsets of str on separate lines
	// You may assume that str has no repeated characters
	// For example, subsets("abc") would print out "", "a", "b", "c", "ab", "ac",
	// "bc", "abc"
	// Order is your choice
	/*
	 * public static void printSubsets(String str) {
	 * for (int i = 0; i < str.length(); i++) {
	 * // System.out.print(str.charAt(i));
	 * StringBuilder sb = new StringBuilder(str.substring(i + 1));
	 * subsetsHelper(sb, str.charAt(i));
	 * // System.out.println();
	 * }
	 * for (int i = 0; i < str.length(); i++) {
	 * System.out.println(str.charAt(i));
	 * }
	 * System.out.println("");
	 * }
	 * 
	 * public static void subsetsHelper(StringBuilder str, char cha) {
	 * if (str.length() < 1) {
	 * return;
	 * }
	 * System.out.print(cha);
	 * System.out.print(str);
	 * System.out.println();
	 * subsetsHelper(str.deleteCharAt(str.length() - 1), cha);
	 * }
	 */

	public static void printSubsets(String str) {
		if (str == null) {
			return;
		}
		subsetsHelper(str, 0, new StringBuilder());
	}

	public static void subsetsHelper(String str, int i, StringBuilder sb) {
		if (i == str.length()) {
			System.out.println(sb);
			return;
		}

		subsetsHelper(str, i + 1, sb);

		sb.append(str.charAt(i));
		subsetsHelper(str, i + 1, sb);

		sb.deleteCharAt(sb.length() - 1);

	}

	// List contains a single String to start.
	// Prints all the permutations of str on separate lines
	// You may assume that str has no repeated characters
	// For example, permute("abc") could print out "abc", "acb", "bac", "bca",
	// "cab", "cba"
	// Order is your choice
	public static void printPermutations(String str) {
		if (str == null) {
			return;
		}
		findPermutations("", str);
	}

	public static void findPermutations(String str, String remaining) {
		if (remaining.isEmpty()) {
			System.out.println(str);
			return;
		}
		for (int i = 0; i < remaining.length(); i++) {
			char c = remaining.charAt(i);
			String next = remaining.substring(0, i) + remaining.substring(i + 1);
			findPermutations(str + c, next);
		}
	}

	// Performs a mergeSort on the given array of ints
	// Precondition: you may assume there are NO duplicates!!!
	public static void mergeSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		int[] temp = new int[arr.length];
		mergeSortHelper(arr, temp, 0, arr.length - 1);
	}

	private static void mergeSortHelper(int[] arr, int[] temp, int left, int right) {
		if (left >= right) {
			return;
		}

		int mid = left + (right - left) / 2;
		mergeSortHelper(arr, temp, left, mid);
		mergeSortHelper(arr, temp, mid + 1, right);
		merge(arr, temp, left, mid, right);
	}

	private static void merge(int[] arr, int[] temp, int left, int mid, int right) {
		int i = left, j = mid + 1, k = left;

		// Merge the two halves into temp
		while (i <= mid && j <= right) {
			if (arr[i] <= arr[j]) {
				temp[k++] = arr[i++];
			} else {
				temp[k++] = arr[j++];
			}
		}

		// Copy remaining elements of left half
		while (i <= mid) {
			temp[k++] = arr[i++];
		}

		// Copy remaining elements of right half
		while (j <= right) {
			temp[k++] = arr[j++];
		}

		// Copy merged range back into original array
		for (int l = left; l <= right; l++) {
			arr[l] = temp[l];
		}
	}

	// copied from last year

	/* public static void merge2(int[] arr, int leftIndex, int middleIndex, int rightIndex) {
		int[] leftArr = Arrays.copyOfRange(arr, leftIndex, middleIndex + 1);
		int[] rightArr = Arrays.copyOfRange(arr, middleIndex + 1, rightIndex + 1);
		int i = 0, j = 0, k = leftIndex;

		// Compare and place elements in the correct order
		while (i < leftArr.length && j < rightArr.length) {
			if (leftArr[i] <= rightArr[j]) {
				arr[k] = leftArr[i++];
			} else {
				arr[k] = rightArr[j++];
			}
			k++;
		}

		// Copy any remaining elements from left array
		while (i < leftArr.length) {
			arr[k++] = leftArr[i++];
		}

		// Copy any remaining elements from right array
		while (j < rightArr.length) {
			arr[k++] = rightArr[j++];
		}
	}

	public static void merge(ArrayList<Integer> list, int leftIndex, int middleIndex, int rightIndex) {
		ArrayList<Integer> leftList = new ArrayList<>(list.subList(leftIndex,
				middleIndex + 1));
		ArrayList<Integer> rightList = new ArrayList<>(list.subList(middleIndex + 1,
				rightIndex + 1));
		int i = 0, j = 0, k = leftIndex;

		// Compare and place elements in the correct order
		while (i < leftList.size() && j < rightList.size()) {
			if (leftList.get(i) <= rightList.get(j)) {
				list.set(k, leftList.get(i++));
			} else {
				list.set(k, rightList.get(j++));
			}
			k++;
		}

		// Copy any remaining elements from left list
		while (i < leftList.size()) {
			list.set(k++, leftList.get(i++));
		}

		// Copy any remaining elements from right list
		while (j < rightList.size()) {
			list.set(k++, rightList.get(j++));
		}
	} */

	// Performs a quickSort on the given array of ints
	// Use the middle element (index n/2) as the pivot
	// Precondition: you may assume there are NO duplicates!!!
	public static void quickSort(int[] ints) {
		if (ints == null || ints.length < 2) {
			return;
		}
		quickSortHelper(ints, 0, ints.length - 1);
	}
	private static void quickSortHelper(int[] ints, int left, int right) {
		if (left >= right) {
			return;
		}

		int pivotIndex = left + (right - left) / 2;
		int pivotValue = ints[pivotIndex];

		// Move pivot to end
		swap(ints, pivotIndex, right);
		int temp = left;

		// Partitioning step
		for (int i = left; i < right; i++) {
			if (ints[i] < pivotValue) {
				swap(ints, i, temp);
				temp++;
			}
		}

		// Move pivot to its final place
		swap(ints, temp, right);

		// Recursively sort elements before and after partition
		quickSortHelper(ints, left, temp - 1);
		quickSortHelper(ints, temp + 1, right);
	}
	private static void swap(int[] ints, int i, int j) {
		int temp = ints[i];
		ints[i] = ints[j];
		ints[j] = temp;
	}

	// Prints a sequence of moves (one on each line)
	// to complete a Towers of Hanoi problem:
	// disks start on tower 0 and must end on tower 2.
	// The towers are number 0, 1, 2, and each move should be of
	// the form "1 -> 2", meaning "take the top disk of tower 1 and
	// put it on tower 2" etc.
	public static void solveHanoi(int startingDisks) {
		if (startingDisks <= 0) {
			return;
		}
		hanoiHelper(startingDisks, 0, 1, 2);
	}

	// have it so the transfer pole changes based off the start and end pole
	public static void hanoiHelper(int startingDisks, int startPole, int transPole, int endPole) {
		if (startingDisks == 0) {
			return;
		}

		hanoiHelper(startingDisks - 1, startPole, endPole, transPole);
		System.out.println(startPole + " -> " + endPole);
		hanoiHelper(startingDisks - 1, transPole, startPole, endPole);

	}

	// You are partaking in a scavenger hunt!
	// You've gotten a secret map to find many of the more difficult
	// items, but they are only available at VERY specific times at
	// specific places. You have an array, times[], that lists at which
	// MINUTE an item is available. Times is sorted in ascending order.
	// Items in the ScavHunt are worth varying numbers of points.
	// You also have an array, points[], same length as times[],
	// that lists how many points each of the corresponding items is worth.
	// Problem is: to get from one location to the other takes 5 minutes,
	// so if there is an item, for example, available at time 23 and another
	// at time 27, it's just not possible for you to make it to both: you'll
	// have to choose!
	// (but you COULD make it from a place at time 23 to another at time 28)
	// Write a method that returns the maximum POINTS you can get.
	// For example, if times = [3, 7, 9]
	// and points = [10, 15, 10]
	// Then the best possible result is getting the item at time 3 and the one at
	// time 9
	// for a total of 20 points, so it would return 20.

	public static int scavHunt(int[] times, int[] points) {
		if (times == null || points == null || times.length == 0 || points.length == 0 || times.length != points.length) {
			return 0;
		}
		int temp = findIndex(0, times);
		if (temp == -1) {
			return 0;
		}

		int n = times.length;
		int[] best = new int[n + 1];
		for (int i = n - 1; i >= temp; i--) {
			int nextIndex = findIndex(times[i] + 5, times);
			if (nextIndex != -1) {
				best[i] = Math.max(points[i] + best[nextIndex], best[i + 1]);
			} else {
				best[i] = Math.max(points[i], best[i + 1]);
			}
		}
		return best[temp];
	}

	public static int findIndex(int val, int[] values) {
		if (values == null || values.length == 0) {
			return -1;
		}
		int left = 0;
		int right = values.length - 1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (values[mid] == val) {
				return mid;
			} else if (values[mid] < val) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return left < values.length ? left : -1;
	}

	// it was lowk easier to do regular binary search instead of tryna maybe convert
	// and reunderstand this bs.
	/*
	 * public static int binarySearch(int[] arr, int key) {
	 * return binarySearchRecursiveHelper(arr, key, 0, arr.length - 1);
	 * }
	 * 
	 * public static int binarySearchRecursiveHelper(int[] arr, int key, int low,
	 * int high) {
	 * if (low > high) {
	 * return -1;
	 * }
	 * 
	 * int mid = (low + high) / 2;
	 * 
	 * if (arr[mid] == key) {
	 * return mid;
	 * }
	 * 
	 * if (arr[mid] > key) {
	 * return binarySearchRecursiveHelper(arr, key, low, mid - 1);
	 * }
	 * 
	 * return binarySearchRecursiveHelper(arr, key, mid + 1, high);
	 * }
	 */
}
