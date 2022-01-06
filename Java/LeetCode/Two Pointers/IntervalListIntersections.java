/** LC 986 -- Two Pointers. **/
import java.util.*;
public class IntervalListIntersections {
    /** use two pointers to keep track the intervals of the two lists;
     * if they intersect, add [max(start1, start2), min(end1, end2)] to the result;
     * List the conditions when they don't intersect with each other;
     * increment the pointer if its interval ends earlier. **/
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> res = new ArrayList<>();
        int m = firstList.length;
        int n = secondList.length;
        int i = 0, j = 0;
        while (i < m && j < n) {
            int[] first = firstList[i];
            int[] second = secondList[j];
            if (first[1] < second[0]) {
                i++;
            } else if (second[1] < first[0]) {
                j++;
            } else {
                int[] inter = new int[2];
                inter[0] = Math.max(first[0], second[0]);
                inter[1] = Math.min(first[1], second[1]);
                res.add(inter);
                if (first[1] < second[1]) {
                    i++;
                } else if (first[1] > second[1]) {
                    j++;
                } else {
                    i++;
                    j++;
                }
            } 
        }

        int[][] intersections = new int[res.size()][2];
        for (int k = 0; k < res.size(); k++) {
            intersections[k] = res.get(k);
        }
        return intersections;
    }

    public static void main(String[] args) {
        IntervalListIntersections ili = new IntervalListIntersections();
        int[][] A = new int[][]{{0,2}, {5,10}, {13, 23}, {24, 25}};
        int[][] B = new int[][]{{1,5}, {8,12}, {15, 24}, {25, 26}};
        System.out.println(Arrays.deepToString(A));
        System.out.println(Arrays.deepToString(B));
        System.out.println(Arrays.deepToString(ili.intervalIntersection(A, B)));
    }
}
