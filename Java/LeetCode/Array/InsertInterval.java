// LC 57 --- Array, Interval
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

public class InsertInterval {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        ArrayList<int[]> newIntervals = new ArrayList<>();
        int[] currInterval = newInterval; 

        for (int[] interval: intervals) { // (): added Interval []: intervals in the iteration
            if (currInterval[1] < interval[0]) { // () []
                newIntervals.add(currInterval);
                currInterval = interval;
            } else {
                if (currInterval[1] <= interval[1]) { //  ([)] or [(,)]
                    currInterval[1] = interval[1];
                    currInterval[0] = Math.min(interval[0], currInterval[0]);
                } else {
                    if (currInterval[0] <= interval[1]) { // [(]) or ([])
                        currInterval[0] = Math.min(interval[0], currInterval[0]);
                    } else {
                        newIntervals.add(interval); // [] ()
                    }        
                }
            }
        }
        newIntervals.add(currInterval);
        int[][] res = new int[newIntervals.size()][2];
        for (int i = 0; i < res.length; i++) {
            res[i] = newIntervals.get(i);
        }
        return res;
    }

    public static void main(String[] args) {
        InsertInterval sol = new InsertInterval();
        int[][] intervals = new int[][]{
            {1, 2},
            {3, 5},
            {6, 7},
            {8, 10},
            {12, 16}
        };

        int[] newInterval = new int[]{4, 8};
        int[][] res= sol.insert(intervals, newInterval);
        Stream.of(res)
            .flatMapToInt(IntStream::of)
                .forEach(System.out::println);
        System.out.println(Arrays.deepToString(res));
    }

}
