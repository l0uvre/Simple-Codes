import java.util.*;
//Leetcode 378 Heap
public class KthSmallestMatrix {

    public int kthSmallest(int[][] matrix, int k) {
        Queue<int[]> pq = new PriorityQueue<>(matrix.length, (a, b) -> Integer.compare(a[0], b[0]));
        for (int i = 0; i < matrix.length; i++) {
            pq.offer(new int[]{matrix[i][0], i, 0});
        }
        
        int res = -1;
        while (!pq.isEmpty() && k > 0) {
            int[] tmp = pq.poll();
            res = tmp[0];
            if (tmp[2] < matrix[tmp[1]].length - 1) {
                pq.offer(new int[]{matrix[tmp[1]][tmp[2] + 1], tmp[1], tmp[2] + 1});
            }
            k--;
        }  
        return res;
    }

    public static void main(String[] args) {
        KthSmallestMatrix ksm = new KthSmallestMatrix();
        int[][] matrix = new int[][] {{1, 5, 9},
                                    {10, 11, 13},
                                    {12, 13, 15}};
        System.out.println(ksm.kthSmallest(matrix, 8));
    }
}
