/** LC 797 -- Graph, DFS, Backtracking. **/
import java.util.*;
public class AllPathsFromSourceToTarget {
    /** just perform a backtracking starting from node 0 and record the
     * path along the dfs, when we reach n - 1, then we add the path. **/
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(0, new ArrayList<>(), graph, res);
        return res;
    }

    private void dfs(int node, List<Integer> path, int[][] graph, List<List<Integer>> res) {
        path.add(node);
        if (node == graph.length - 1) {
            res.add(new ArrayList<>(path));
        }
        for (int next : graph[node]) {
            dfs(next, path, graph, res);
        }
        path.remove(path.size() - 1);
    }

    public static void main(String[] args) {
        AllPathsFromSourceToTarget sol = new AllPathsFromSourceToTarget();
        int[][] graph = new int[][]{
            {1, 2},
                {3},
                {3},
                {}
        };
        System.out.println(sol.allPathsSourceTarget(graph));

        graph = new int[][]{
            {4, 3, 1},
                {3, 2, 4},
                {3},
                {4},
                {}
        };
        System.out.println(sol.allPathsSourceTarget(graph));
    }
}
