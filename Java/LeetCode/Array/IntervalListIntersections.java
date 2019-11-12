import java.util.*;

public class IntervalListIntersections {

     public int[][] intervalIntersection(int[][] A, int[][] B) {
        if (A == null || B == null) {
            return null;
        }        
        int m = A.length;
        int n = B.length;
        int i = 0, j = 0;
        List<int[]> res = new LinkedList<>();
        while (i < m && j < n) {
            if (A[i][1] < B[j][0]) {
                i++;
            } else if (B[j][1] < A[i][0]) {
                j++;
            } else {
                int left = Math.max(A[i][0], B[j][0]);
                int right = Math.min(A[i][1], B[j][1]);
                res.add(new int[]{left, right}); 
                if (A[i][1] < B[j][1]) {
                    i++;
                } else {
                    j++;
                }
            }
        }
        return res.toArray(new int[res.size()][]);
    }
    
    private static void print(int[][] A) {
        for (int[] a : A) {
            System.out.print("[ ");
            for (int ele : a) {
                System.out.print(ele + " ");
            }
            System.out.print("] ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        IntervalListIntersections ili = new IntervalListIntersections();
        int[][] A = new int[][]{{0,2}, {5,10}, {13, 23}, {24, 25}};
        int[][] B = new int[][]{{1,5}, {8,12}, {15, 24}, {25, 26}};
        print(A);
        print(B);
        print(ili.intervalIntersection(A, B));
    }
}
