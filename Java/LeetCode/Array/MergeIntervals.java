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
