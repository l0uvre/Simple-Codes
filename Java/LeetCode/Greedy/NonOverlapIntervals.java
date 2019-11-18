//https://leetcode.com/problems/non-overlapping-intervals/
//
import java.util.*;

public class NonOverlapIntervals {

    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }            
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        int[] current = intervals[0];
        int res = 0;
        for (int i = 1; i < intervals.length; i++) {
            int[] next = intervals[i];
            if (next[0] < current[1]) {
                if (current[1] > next[1]) {
                    current = next;
                }
                res++;
            } else {
                current = next;
            }
        }
        return res;
    }
    
    public static void main(String[] args) {
        NonOverlapIntervals noi = new NonOverlapIntervals();
        int[][] intervals = new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        System.out.println(noi.eraseOverlapIntervals(intervals));
        intervals = new int[][]{{1, 2}, {1, 2}, {1, 2}};
        System.out.println(noi.eraseOverlapIntervals(intervals));
        intervals = new int[][]{{2, 3}, {1, 2}};
        System.out.println(noi.eraseOverlapIntervals(intervals));
        intervals = new int[][]{{1, 5}, {3, 4}, {4, 5}};
        System.out.println(noi.eraseOverlapIntervals(intervals));
    }
}
