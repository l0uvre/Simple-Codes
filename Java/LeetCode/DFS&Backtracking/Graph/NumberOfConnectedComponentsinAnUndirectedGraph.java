/** LC 323 --- Graph, Union Find  Can also be solve by DFS/BFS **/
import java.util.*;

public class NumberOfConnectedComponentsinAnUndirectedGraph {

    public int countComponents(int n, int[][] edges) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        int res = 0;
        for (int[] edge : edges) {
            adjList.putIfAbsent(edge[0], new ArrayList<>());
            adjList.putIfAbsent(edge[1], new ArrayList<>());
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, adjList, visited);
                res += 1;
            }
        }
        return res;
    }

    private void dfs(int curr, Map<Integer, List<Integer>> adjList,
            boolean[] visited) {
        visited[curr] = true;
        List<Integer> neighbors = adjList.get(curr);

         if (neighbors == null) {
            return;
        }
        for (int neighbor : neighbors) {
            if (!visited[neighbor]) {
                dfs(neighbor, adjList, visited);
            }
        }
    }

    public static void main(String[] args) {
        NumberOfConnectedComponentsinAnUndirectedGraph sol =
            new NumberOfConnectedComponentsinAnUndirectedGraph();
        int n = 5;
        int[][] edges = {
            {0, 1},
            {1, 2},
            {3, 4}
        };
        System.out.println(sol.countComponents(n, edges));
    }
}
