
import java.util.HashMap;

public class DynamicProgramming {

    // Every day for the rest of the year, you're going to be given a choice between
    // two jobs to do:
    // one that is LOW stress, and one that is HIGH stress. Each job pays out a
    // dollar amount;
    // *usually* the high stress jobs pay more. However, after doing a high stress
    // job, you need to
    // REST for a day.

    // Given a list of all the payouts for all the low stress and high stress jobs,
    // what is the most amount of money you can get?

    // You can assume lowPayouts.length == highPayouts.length
    public static int hiLoStress(int[] lowPayouts, int[] highPayouts) {
        HashMap<String, Integer> memo = new HashMap<>();
        return hiLoStressHelper(lowPayouts, highPayouts, 0, true, memo);
    }

    public static int hiLoStressHelper(int[] lowPayouts, int[] highPayouts, int day, boolean rested,
            HashMap<String, Integer> memo) {
        if (day >= lowPayouts.length)
            return 0;

        String key = day + "," + rested;
        if (memo.containsKey(key))
            return memo.get(key);

        int maxPayout;
        int lowPayout = lowPayouts[day] + hiLoStressHelper(lowPayouts, highPayouts, day + 1, true, memo);

        if (rested) {
            int highPayout = highPayouts[day] + hiLoStressHelper(lowPayouts, highPayouts, day + 1, false, memo);
            maxPayout = Math.max(lowPayout, highPayout);
        } else
            maxPayout = hiLoStressHelper(lowPayouts, highPayouts, day + 1, true, memo);
        memo.put(key, maxPayout);
        return maxPayout;
    }

    // You are partaking in a scavenger hunt!
    // You've gotten a secret map to find many of the more difficult
    // items, but they are only available at VERY specific times at
    // specific places. You have an array, times[], that lists at which
    // MINUTE an item is available, in increasing order.
    // Items in the ScavHunt are worth varying numbers of points.
    // You also have an array, points[], same length as times[],
    // that lists how many points each of the corresponding items is worth.
    // Problem is: to get from one location to the other takes 5 minutes,
    // so if there is an item, for example, available at time 23 and another
    // at time 27, it's just not possible for you to make it to both: you'll
    // have to choose!
    // Write a method that returns the maximum POINTS you can get.

    public static int scavHunt(int[] times, int[] points) {
        if (times == null || points == null || times.length == 0 || points.length == 0
                || times.length != points.length) {
            return 0;
        }
        int temp = findIndex(0, times);
        if (temp == -1) {
            return 0;
        }

        HashMap<Integer, Integer> memo = new HashMap<>();
        return scavHuntHelper(times, points, temp, memo);
    }

    public static int scavHuntHelper(int[] times, int[] points, int temp, HashMap<Integer, Integer> memo) {
        if (temp >= times.length) {
            return 0;
        }

        if (memo.containsKey(temp))
            return memo.get(temp);

        int next = findIndex(times[temp] + 5, times);
        int keep = points[temp];
        if (next != -1) {
            keep += scavHuntHelper(times, points, next, memo);
        }
        int skip = scavHuntHelper(times, points, temp + 1, memo);
        int best = Math.max(keep, skip);
        memo.put(temp, best);
        return best;
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

    // Uses memoization to calculate the route which grants the most cookies,
    // starting at [0][0], only going right or down at each point

    public static int dynamicCookies(int[][] cookieGrid) {
        HashMap<String, Integer> memo = new HashMap<>();
        return dynamicCookiesHelper(cookieGrid, 0, 0, memo);
    }

    public static int dynamicCookiesHelper(int[][] cookieGrid, int row, int col, HashMap<String, Integer> memo) {
        if (!validPoint(cookieGrid, row, col))
            return 0;
        if (row == cookieGrid.length - 1 && col == cookieGrid[0].length - 1)
            return cookieGrid[row][col];

        String key = row + "," + col;
        if (memo.containsKey(key))
            return memo.get(key);

        int down = dynamicCookiesHelper(cookieGrid, row, col + 1, memo);
        int right = dynamicCookiesHelper(cookieGrid, row + 1, col, memo);
        int best = Math.max(down, right);

        memo.put(key, cookieGrid[row][col] + best);
        return cookieGrid[row][col] + best;
    }

    private static boolean validPoint(int[][] cookieGrid, int row, int col) {
        return row >= 0 && row < cookieGrid.length && col >= 0 && col < cookieGrid[0].length;
    }
}
