/** LC 310 - BFS, topological sort. **/
import java.util.*;
public class MinHeightTrees {
    /** 
     * Basic idea : generally, we'd like to pick the middle point of the longest
     * path graph; a leaf node is always the worse candidate than its adjacent non-leaf node;
     *
     * Perform a topological order BFS from all leaf nodes (degree 1), then remove the leaves, 
     * update the degrees, and repeat the process until there's only 1 or 2 nodes left which 
     * are the answer. **/
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        if (n == 1) {
            res.add(0);
            return res;
        } 
        int[] degrees = new int[n];
        // build the undirected graph
        Map<Integer, Set<Integer>> graph = new HashMap<>();

        for (int[] edge : edges) {
            degrees[edge[0]] += 1;
            degrees[edge[1]] += 1;
            graph.putIfAbsent(edge[0], new HashSet<>());
            graph.putIfAbsent(edge[1], new HashSet<>());
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        // queue for BFS
        Queue<Integer> q = new LinkedList<>();
        // all unvisited nodes, remove vistied nodes per level
        Set<Integer> remainingNodes = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (degrees[i] == 1) {
                q.offer(i);
            }
            remainingNodes.add(i);
        }
        while (!q.isEmpty()) {
            if (remainingNodes.size() <= 2) {
                res.addAll(remainingNodes);
                return res;
            }
            for (int i = q.size(); i > 0; i--) {
                int curr = q.poll();
                remainingNodes.remove(curr);
                for (int neighbor : graph.get(curr)) {
                    degrees[neighbor] -= 1;
                    graph.get(neighbor).remove(curr);
                    if (degrees[neighbor] == 1) {
                        q.offer(neighbor);
                    }
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {
        MinHeightTrees sol = new MinHeightTrees();
        int n = 4;
        int[][] edges = new int[][]{
            {1, 0},
                {1,2},
                {1,3}
        };
        System.out.println(sol.findMinHeightTrees(n, edges));

        n = 6;
        edges = new int[][]{
            {3, 0},
                {3, 1},
                {3, 2},
                {3, 4},
                {5, 4}
        };
        System.out.println(sol.findMinHeightTrees(n, edges));

        n = 1;
        edges = new int[][]{};
        System.out.println(sol.findMinHeightTrees(n, edges));
    }
}
