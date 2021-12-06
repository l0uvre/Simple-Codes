/*** LC 56 -- Array, Interval ***/
import java.util.*;

class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }

    @Override
    public String toString() {
        return "[" + start + "," + end + "]";
    }
 }

public class MergeIntervals {
   
    /** Sort the intervals based on the starting point,
     * and merge the adjacent ones accordingly, O(nlogn) **/
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[][]{};
        } else {
            List<int[]> merged = new ArrayList<>();
            Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
            merged.add(intervals[0]);
            for (int i = 1; i < intervals.length; i++) {
                int[] last = merged.get(merged.size() - 1);
                int[] curr = intervals[i];
                /** if the current interval overlaps with the
                 * last one stored **/
                if (last[1] >= curr[0]) {
                    /** merge these two, pick the larger end **/
                    last[1] = Math.max(last[1], curr[1]);
                } else {
                    merged.add(curr);
                }
            }
            int[][] res = new int[merged.size()][2];
            for (int i = 0; i < res.length; i++) {
                res[i] = merged.get(i);
            }
            return res;
        }
    }
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() < 2) {
            return intervals;
        }
        
        List<Interval> res = new LinkedList<>();
        Collections.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));
        
        for (Interval interval : intervals) {
            if (res.isEmpty()) {
                res.add(interval);
            } else {
                Interval last = res.get(res.size() - 1);
                if (interval.start <= last.end) {
                    last.end = Math.max(interval.end, last.end);
                } else {
                    res.add(interval);
                }
            }
        }
        return res;
    }

    private static List<Interval> makeIntervals(int[][] vals) {
        List<Interval> res = new LinkedList<>();

        for (int[] pair : vals) {
           res.add(new Interval(pair[0], pair[1])); 
        }
        return res;
    }

    private static void showIntervalList(List<Interval> intervals) {
        String str = "[";
        for (Interval intl : intervals) {
            str += intl.toString();
        }
        str += "]";
        System.out.println(str);
    }

    public static void main(String[] args) {
        int[][] vals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        List<Interval> intervals = makeIntervals(vals);
        MergeIntervals sol = new MergeIntervals();
        showIntervalList(sol.merge(intervals)); 
    }
}
