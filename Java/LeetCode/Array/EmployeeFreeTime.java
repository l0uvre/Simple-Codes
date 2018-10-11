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
 
public class EmployeeFreeTime {

    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
       List<Interval> res = new LinkedList<>();
       List<Interval> intervals = new ArrayList<>();
       for (List<Interval> interval : schedule) {
           for (Interval intvl : interval) {
               intervals.add(intvl);
           }
       }
       if (intervals.size() == 0) {
           return res;
       }
       Collections.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));
       int end = intervals.get(0).end;

       for (int i = 1; i < intervals.size(); i++) {
           Interval curr = intervals.get(i);
           if (curr.start > end) {
               res.add(new Interval(end, curr.start));
           }
           end = Math.max(end, curr.end);
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
        EmployeeFreeTime sol = new EmployeeFreeTime();
        int[][] vals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        /**** TEST 1 ****/
        vals = new int[][]{{1, 2}, {5, 6}};
        List<Interval> intervals = makeIntervals(vals);
        List<List<Interval>> schedule = new LinkedList<>();
        schedule.add(intervals);
        vals = new int[][]{{1, 3}, {4, 10}};
        intervals = makeIntervals(vals);
        schedule.add(intervals);

        System.out.println(sol.employeeFreeTime(schedule));


        /*** TEST 2 ***/
        schedule = new LinkedList<>();
        vals = new int[][]{{1, 3}, {6, 7}};
        intervals = makeIntervals(vals);
        schedule.add(intervals);
        vals = new int[][]{{2, 4}};
        intervals = makeIntervals(vals);
        schedule.add(intervals);
        vals = new int[][]{{2, 5}, {9, 12}};
        intervals = makeIntervals(vals);
        schedule.add(intervals);

        System.out.println(sol.employeeFreeTime(schedule));
    }

}
