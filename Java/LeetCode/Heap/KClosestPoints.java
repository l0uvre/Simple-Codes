/***LC 973 -- Heap/ Quick Select **/
import java.util.*;

public class KClosestPoints {

    /*** O(Nlogk) Using priority queue **/
    public int[][] kClosestHeap(int[][] points, int k) {
        /** use a maxHeap of size at most k to
         * store the 1 - kth closest points **/
        Queue<int[]> maxHeap = new PriorityQueue<>((a, b) ->
                Integer.compare(b[0] * b[0] + b[1] * b[1], 
                    a[0] * a[0] + a[1] * a[1]));

        for (int[] point : points) {
            if (maxHeap.size() <= k) {
                maxHeap.offer(point);
            }
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        int[][] closestPoints = new int[k][2];
        for (int i = k - 1; i >= 0; i--) {
            int[] point = maxHeap.poll();
            closestPoints[i] = point;
        }
        return closestPoints;
    }

    /** use quick select O(n) average O(n^2) worst **/
    public int[][] kClosest(int[][] points, int k) {
        int n = points.length;
        int left = 0, right = n - 1;
        while (left < right) {
            int j = partition(points, left, right);
            /** should be k - 1 since it's [0...k-1] 
             * k closest points **/
            if (j < k) {
                left = j + 1;
            } else if (j > k) {
                right = j - 1;
            } else {
                break;
            }
        }

        return Arrays.copyOf(points, k);
    }

    /** partition the array so that 
     * points[left..j-1] <= points[j] <= points[j+1, right]
     * **/
    private int partition(int[][] points, int left, int right) {
        int[] pivot = points[left];
        int i = left;
        int j = right + 1;
        while (true) {
            do {
                i++;
            } while (i <= right && compare(points[i], pivot) < 0);

            do {
                j--;
            } while (compare(points[j], pivot) > 0);

            if (i >= j) {
                break;
            }
            swap(points, i, j);
        }
        swap(points, left, j);
        return j;

    }

    private void swap(int[][] points, int i, int j) {
        int[] tmp = points[i];
        points[i] = points[j];
        points[j] = tmp;
    }

    private int compare(int[] p1, int[] p2) {
        int d1 = p1[0] * p1[0] + p1[1] * p1[1];
        int d2 = p2[0] * p2[0] + p2[1] * p2[1];
        return d1 - d2;
    }


    public static void main(String[] args) {
        KClosestPoints sol = new KClosestPoints();   
        int[][] points = new int[][]{
            {3,3},{5,-1},{-2,4}
        };
        int k = 2;
        System.out.println(Arrays.deepToString(sol.kClosest(points, k)));

        points = new int[][] {
            {1,3},{-2,2}
        };
        k = 1;
        System.out.println(Arrays.deepToString(sol.kClosest(points, k)));
    }
}
