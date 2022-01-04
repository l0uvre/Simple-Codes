/** LC 863 -- DFS/BFS. **/
import java.util.*;
public class AllNodesDistanceK {
    /** 
     * Build an undirected graph by doing a dfs from root;
     * O(N)
     *
     * Do a BFS starting from the target nodes in the graph;
     * O(N);
     *
     * Overall  O(N).
     * **/
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        Map<Integer, Set<Integer>> edges = new HashMap<>();
        buildGraph(root, edges);
        List<Integer> res = new ArrayList<>();
        bfs(target, res, edges, k);
        return res;
    }

    private void buildGraph(TreeNode node, Map<Integer, Set<Integer>> edges ) {
        if (node != null) {
            edges.putIfAbsent(node.val, new HashSet<>());
            if (node.left != null) {
                edges.get(node.val).add(node.left.val);
                edges.putIfAbsent(node.left.val, new HashSet<>());
                edges.get(node.left.val).add(node.val);
                buildGraph(node.left, edges);
            }

            if (node.right != null) {
                edges.get(node.val).add(node.right.val);
                edges.putIfAbsent(node.right.val, new HashSet<>());
                edges.get(node.right.val).add(node.val);
                buildGraph(node.right, edges);
            }
        }
    }

    private void bfs(TreeNode target, List<Integer> res,
            Map<Integer, Set<Integer>> edges, int k) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        q.offer(target.val);
        int steps = 0;
        while (!q.isEmpty()) {
            if (steps > k) {
                break;
            }
            for (int i = q.size(); i > 0; i--) {
                int curr = q.poll();
                if (!visited.contains(curr)) {
                    visited.add(curr);
                    if (steps == k) {
                        res.add(curr);
                    }
                    for (int neighbor : edges.get(curr)) {
                        if (!visited.contains(neighbor)) {
                            q.offer(neighbor);
                        }
                    }
                }
            }
            steps += 1;
        }
    }

}
