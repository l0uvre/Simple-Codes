/***LC 261 -- Graph, DFS/BFS ***/
import java.util.*;

public class GraphValidTree {
    /** DFS approach **/
    public boolean validTreeDFS(int n, int[][] edges) {
        if (edges == null) {
            return n == 1;
        } else {
            /** the map is the adjacent list representation
             * of the graph **/
            Map<Integer, Set<Integer>> map = new HashMap<>();
            
            /** when using dfs or bfs, we treat the graph as
             * undirected one ***/
            for (int[] edge : edges) {
                map.putIfAbsent(edge[0], new HashSet<>());     
                map.get(edge[0]).add(edge[1]);
                map.putIfAbsent(edge[1], new HashSet<>());     
                map.get(edge[1]).add(edge[0]);
            }

            Stack<Integer> stack = new Stack<>();
            boolean[] visited = new boolean[n];
            stack.push(0);
            visited[0] = true;
            while (!stack.isEmpty()) {
                int curr = stack.pop();
                Set<Integer> neighbors = map.get(curr);
                if (neighbors != null) {
                    for (int neighbor : neighbors) {
                        /** if we reached this node before
                         * that means we have detected a cycle**/
                        if (visited[neighbor]) {
                            return false;
                        } else {
                            stack.push(neighbor);
                            /*** since the graph is undirected
                             * we need to remove the predeccessor
                             * from its edge***/
                            map.get(neighbor).remove(curr);
                            /** we need to mark the node
                             * upon adding it to the stack/queue.
                             * Otherwise, we may add the node to the
                             * stack/queue before that node should be visited
                             * first. **/
                            visited[neighbor] = true;
                        }
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                /** there is something else 
                 * than the valid tree we dfs-ed **/
                if (visited[i] == false) {
                    return false;
                }
            }

            return true;
        }
    }

    /** BFS approach **/
    public boolean validTree(int n, int[][] edges) {
        if (edges == null) {
            return n == 1;
        } else {
            /** the map is the adjacent list representation
             * of the graph **/
            Map<Integer, Set<Integer>> map = new HashMap<>();
            
            /** when using dfs or bfs, we treat the graph as
             * undirected one ***/
            for (int[] edge : edges) {
                map.putIfAbsent(edge[0], new HashSet<>());     
                map.get(edge[0]).add(edge[1]);
                map.putIfAbsent(edge[1], new HashSet<>());     
                map.get(edge[1]).add(edge[0]);
            }

            Queue<Integer> q = new LinkedList<>();
            boolean[] visited = new boolean[n];
            q.offer(0);
            while (!q.isEmpty()) {
                int curr = q.poll();
                /** another approach: we mark the node when
                 * we expand it but it requires a double check.
                 **/
                if (visited[curr]) {
                    return false;
                } else {
                    visited[curr] = true;
                }
                Set<Integer> neighbors = map.get(curr);
                if (neighbors != null) {
                    for (int neighbor : neighbors) {
                        /** if we reached this node before
                         * that means we have detected a cycle**/
                        if (visited[neighbor]) {
                            return false;
                        } else {
                            q.offer(neighbor);
                            /*** since the graph is undirected
                             * we need to remove the predeccessor
                             * from its edge***/
                            map.get(neighbor).remove(curr);
                        }
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                /** there is something else 
                 * than the valid tree we dfs-ed **/
                if (visited[i] == false) {
                    return false;
                }
            }

            return true;
        }
    }

    public static void main(String[] args) {
        GraphValidTree sol = new GraphValidTree();  
        int n = 5;
        int[][] edges = new int[][]{
            {0,1},
                {0,2},
                {0,3},
                {1,4}
        };
        System.out.println(sol.validTree(n, edges));

        n = 1;
        edges = new int[][]{};
        System.out.println(sol.validTree(n, edges));

        n = 5;
        edges = new int[][]{
                {0,1},
                {1,2},
                {2,3},
                {1,3},
                {1,4}

        };
        System.out.println(sol.validTree(n, edges));

        n = 6;
        edges = new int[][]{
                {0,1},
                {1,2},
                {2,3},
                {1,3},
                {1,4}

        };
        System.out.println(sol.validTree(n, edges));
        
    }
}
