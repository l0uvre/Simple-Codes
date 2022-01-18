/** Lintcode 839 -- Two Pointers, Interval;
 * https://leetcode.com/discuss/interview-question/algorithms/124616/facebook-merge-two-interval-lists. **/
import java.util.*;
public class MergeTwoSortedIntervalLists {
    /** test data :
     * [(1,5),(10,14),(16,18)]
     * [(2,6),(8,10),(11,20)]; -> [(1,6),(8,20)];
     * 
     *
     *[(1,3),(4,7),(10,10)]
     * [(2,5),(6,8),(11,12)]; -> [(1,8),(10,10),(11,12)].
     **/
    class Interval {
       int start, end;
       Interval(int start, int end) {
           this.start = start;
           this.end = end;
       }
    }

    /** use a two pointer scheme 
     * every time we check whether the two interval in the different list overlap,
     * if so, we merge them and add it to the result list ;
     * if not, we add the smaller interval to the result list;
     * if one pointer reaches the end, we add intervals from the other list;
     * every time we add an interval to the result list, we check whether it can
     * be merged into the end. **/
    public List<Interval> mergeTwoInterval(List<Interval> list1, List<Interval> list2) {
        if (list1 == null || list1.size() == 0) {
            return list2;
        } else if (list2 == null || list2.size() == 0) {
            return list1;
        } else {
            List<Interval> res = new ArrayList<>();
            int m = list1.size();
            int n = list2.size();
            int i = 0;
            int j = 0;
            while (i < m || j < n) {
                if (i >= m) {
                    addToEnd(res, list2.get(j));
                    j++;
                } else if (j >= n) {
                    addToEnd(res, list1.get(i));
                    i++;
                } else {
                    Interval interval1 = list1.get(i);
                    Interval interval2 = list2.get(j);
                    if (interval1.end < interval2.start) {
                        addToEnd(res, interval1);
                        i++;
                    } else if (interval2.end < interval1.start) {
                        addToEnd(res, interval2);
                        j++;
                    } else {
                        Interval newInterval = new Interval(Math.min(interval1.start,
                                    interval2.start), Math.max(interval1.end, 
                                        interval2.end));
                        addToEnd(res, newInterval);
                        if (interval1.end < interval2.end) {
                            i++;
                        } else if (interval2.end < interval1.end) {
                            j++;
                        } else {
                            i++;
                            j++;
                        }
                    }
                }
            }
            return res;
        }
    }

    /** convinient helper function to DRY. **/
    private void addToEnd(List<Interval> res, Interval interval) {
        if (!res.isEmpty()) {
            Interval last = res.get(res.size() - 1);
            if (last.end >= interval.start) {
                last.end = Math.max(last.end, interval.end);
                return;
            }
        }
        res.add(interval);
    }

}
