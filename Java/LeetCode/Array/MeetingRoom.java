/** LC 252 --- Array, Sorting ***/
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
 
public class MeetingRoom {

    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return true;
        } else {
            Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
            for (int i = 0; i < intervals.length - 1; i++) {
                int[] curr = intervals[i];
                int[] next = intervals[i + 1];
                if (curr[1] > next[0]) {
                    return false;
                }
            }
            return true;
        }
    }


    public boolean canAttendMeetings(List<Interval> intervals) {
       if (intervals == null || intervals.size() <= 1) {
            return true; 
       } 
       Collections.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));
       for (int i = 0; i < intervals.size() - 1; i++) {
           Interval curr = intervals.get(i);
           Interval next = intervals.get(i + 1);
           if (next.start < curr.end) {
                return false; 
           }
       } 
       return true;
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
        MeetingRoom sol = new MeetingRoom();
        System.out.println(sol.canAttendMeetings(vals));
        System.out.println(sol.canAttendMeetings(intervals));

        vals = new int[][]{{0,30},{5,10},{15,20}};
        intervals = makeIntervals(vals);
        System.out.println(sol.canAttendMeetings(vals));
        System.out.println(sol.canAttendMeetings(intervals));

        vals = new int[][]{{7,10},{2,4}};
        intervals = makeIntervals(vals);
        System.out.println(sol.canAttendMeetings(vals));
        System.out.println(sol.canAttendMeetings(intervals));
    }
}
