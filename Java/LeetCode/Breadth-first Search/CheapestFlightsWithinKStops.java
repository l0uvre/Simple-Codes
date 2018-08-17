import java.util.*;
class CheapestFlightsWithinKStops {
    class City {
        int id;
        int cost;
        City(int i, int c) {
            id = i;
            cost = c;
        }
    }
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        for (int[] flight: flights) {
            if (!graph.containsKey(flight[0])) {
                graph.put(flight[0], new HashMap<>());
            }
            graph.get(flight[0]).put(flight[1], flight[2]);
        }
        int minCost = Integer.MAX_VALUE;
        Queue<City> q = new LinkedList<>();
        q.offer(new City(src, 0));
        int stop = -1;
        while (!q.isEmpty()) {
            if (stop > K) {
                break;
            }
            for (int i = q.size(); i > 0; i--) {
                City curr = q.poll();
                if (curr.id == dst) {
                    minCost = Math.min(curr.cost, minCost);
                }
                Map<Integer, Integer> neighbors = graph.get(curr.id);
                if (neighbors != null) {
                    for (int key: neighbors.keySet()) {
                        if (curr.cost + neighbors.get(key) < minCost) {
                            q.offer(new City(key, curr.cost + neighbors.get(key)));
                        }
                    }
                }
            }
            stop++;
        }
        
        return minCost == Integer.MAX_VALUE ? -1 : minCost;
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
      int[][] edges2 = {{0,1,5},{1,2,5},{0,3,2},{3,1,2},{1,4,1},{4,2,1}};
      src = 0;
      dst = 2;
      K = 2;
      System.out.println(sol.findCheapestPrice(n, edges2, src, dst, K));

    }

}
