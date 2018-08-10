import java.util.*;
class CheapestFlightsWithinKStops {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        for (int[] flight: flights) {
            if (!graph.containsKey(flight[0])) {
                graph.put(flight[0], new HashMap<>());
            }
            graph.get(flight[0]).put(flight[1], flight[2]);
        }
        
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        pq.offer(new int[]{0, src, -1}); // cost, station, remaing stops.
        
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            if (curr[1] == dst) {
                return curr[0];
            }
            if (curr[2] < K) {
                Map<Integer, Integer> neighbor = graph.getOrDefault(curr[1], new HashMap<>());
                for (int key: neighbor.keySet()) {
                    pq.offer(new int[]{neighbor.get(key) + curr[0], key, curr[2] + 1});
                }
            }
        }
        
        return -1;
    }

    public static void main(String[] args) {
      CheapestFlightsWithinKStops sol = new CheapestFlightsWithinKStops();
      int n = 5;
      int[][] flights = {{4,1,1},{1,2,3},{0,3,2},{0,4,10},{3,1,1},{1,4,3}};
      int src = 2;
      int dst = 1;
      int K = 1;
      System.out.println(sol.findCheapestPrice(n, flights, src, dst, K));
      n = 3;
      int[][] edges = {{0,1,100},{1,2,100},{0,2,500}};
      src = 0;
      dst = 2;
      K = 1;
      System.out.println(sol.findCheapestPrice(n, edges, src, dst, K));
    }

}
