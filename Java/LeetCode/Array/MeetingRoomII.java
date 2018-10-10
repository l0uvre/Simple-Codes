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
 
public class MeetingRoomII {

    public int minMeetingRooms(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0) {
            return 0; 
        } else if (intervals.size() == 1) {
            return 1;
        }
        int res = 1;
        Collections.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));
        Queue<Integer> pq = new PriorityQueue<>();
        pq.offer(intervals.get(0).end);

        for (int i = 1; i < intervals.size(); i++) {
           Interval curr = intervals.get(i);
           
           if (curr.start >= pq.peek()) {
               pq.poll();
           } 
           pq.offer(curr.end);
        } 
       return pq.size();
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
        int[][] vals = {{0, 20}, {5, 30}, {21, 25}};
        List<Interval> intervals = makeIntervals(vals);
        MeetingRoomII sol = new MeetingRoomII();
        System.out.println(sol.minMeetingRooms(intervals));
        vals = new int[][]{{0,30},{5,10},{15,20}};
        intervals = makeIntervals(vals);
        System.out.println(sol.minMeetingRooms(intervals));
        vals = new int[][]{{7,10},{2,4}};
        intervals = makeIntervals(vals);
        System.out.println(sol.minMeetingRooms(intervals));
        vals = new int[][]{{5,8},{6,8}};
        intervals = makeIntervals(vals);
        System.out.println(sol.minMeetingRooms(intervals));
    }
}
