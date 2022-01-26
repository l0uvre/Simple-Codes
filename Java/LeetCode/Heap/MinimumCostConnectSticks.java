/** LC 1167 -- Heap, Greedy. **/
import java.util.*;
public class MinimumCostConnectSticks {
    /** use a minHeap to merge the two shortest
     * sticks every time. **/
    public int connectSticks(int[] sticks) {
        int cost = 0;
        Queue<Integer> minHeap = new PriorityQueue<>();
        for (int stick : sticks) {
            minHeap.offer(stick);
        }

        while (minHeap.size() > 1) {
            int merged = minHeap.poll() + minHeap.poll();
            cost += merged;
            minHeap.offer(merged);
        }
        return cost;
    }

    public static void main(String[] args) {
        MinimumCostConnectSticks sol = new MinimumCostConnectSticks(); 
        int[] sticks = new int[]{2, 4, 3};
        System.out.println(sol.connectSticks(sticks));

        sticks = new int[]{1, 8, 3, 5};
        System.out.println(sol.connectSticks(sticks));
    }

}
