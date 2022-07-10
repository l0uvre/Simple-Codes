// LC 57 --- Array, Interval
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

public class InsertInterval {


    /**
     * loop over the sorted input intervals;
     * if the newInterval doesn't overlap with the current interval during the loop,
     * then add the smaller interval into the result; otherwise merge it 
     * into the newInterval and then continue;
     * */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> resList = new ArrayList<>();

        int[] pInterval = newInterval.clone();
        for (int[] interval : intervals) {
            if (pInterval[1] < interval[0]) {
                resList.add(pInterval);
                pInterval = interval;
            } else if (pInterval[0] > interval[1]) {
                resList.add(interval);
            } else { /** the newInterval overlaps with the interval during the loop. **/
                pInterval[0] = Math.min(pInterval[0], interval[0]);
                pInterval[1] = Math.max(pInterval[1], interval[1]);
            }
        }

        resList.add(pInterval);
        int[][] res = new int[resList.size()][2];
        for (int i = 0; i < res.length; i++) {
            res[i] = resList.get(i);
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
