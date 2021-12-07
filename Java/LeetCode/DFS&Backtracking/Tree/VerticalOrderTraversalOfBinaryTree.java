/*** LC 987 --- Tree, DFS, Heap ***/
import java.util.*;
public class VerticalOrderTraversalOfBinaryTree {


    /*** using bfs level traversal;
     * store nodes with column indices in a hash map,
     * break the tie for nodes in the same column and row.**/
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        /** use tree map to traverse the culumns in order. 
         * use a heap to maintain the nodes in level order and break the tie**/
        Map<Integer, Queue<int[]>> nodesInCol = new TreeMap<>();

        bfs(root, nodesInCol);
        for (int col : nodesInCol.keySet()) {
            Queue<int[]> colNodes = nodesInCol.get(col);
            List<Integer> nodeList = new LinkedList<>();
            while (!colNodes.isEmpty()) {
                nodeList.add(colNodes.poll()[1]);
            }
            res.add(nodeList);
        }
        return res;

    }
    /** wrap the treeNode with column Index**/
    private class CustomNode {
        TreeNode node;
        int col;
        int level;
        CustomNode (TreeNode n, int c, int l) {
            node = n;
            col = c;
            level = l;
        }
    }

    private void bfs(TreeNode root, 
            Map<Integer, Queue<int[]>> nodesDict) {
        Queue<CustomNode> q = new LinkedList<>();
        q.offer(new CustomNode(root, 0, 0));
        while (!q.isEmpty()) {
            CustomNode cNode = q.poll();
            int currCol = cNode.col;
            int level = cNode.level;
            /** use heap to make the node in the lower level smaller,
             * the smaller node come first in the same level **/
            nodesDict.putIfAbsent(currCol, new PriorityQueue<>(
                (a, b) -> {
                    if (a[0] != b[0]) {
                        return Integer.compare(a[0], b[0]);
                    } else {
                        return Integer.compare(a[1], b[1]);
                    }
                }));
            nodesDict.get(currCol).offer(new int[]{level, cNode.node.val});
            if (cNode.node.left != null) {
                q.offer(new CustomNode(cNode.node.left, currCol - 1, level + 1));
            }
            if (cNode.node.right != null) {
                q.offer(new CustomNode(cNode.node.right, currCol + 1, level + 1));
            }
        }
    }

    /*** using dfs preorder traversal;
     * store nodes with column indices in a hash map,
     * break the tie by using a heap with a custom comparator **/
    public List<List<Integer>> verticalTraversalDFS(TreeNode root) {
        /*** store the nodes per column into the hashmap, the root index is 0,
         * left -= 1, right += 1 **/
        List<List<Integer>> res = new LinkedList<>();
        /** Use treeMap so that the indices are in order **/
        Map<Integer, Queue<int[]>> nodesAtCol = new TreeMap<>();
        dfs(root, 0, 0, nodesAtCol);
        for (int i : nodesAtCol.keySet()) {
            List<Integer> col = new ArrayList<>();
            Queue<int[]> colPQ = nodesAtCol.get(i);
            while (!colPQ.isEmpty()) {
                col.add(colPQ.poll()[1]);
            }
            res.add(col);
        }
        return res;
    }

    private void dfs(TreeNode node, int level, int col, 
            Map<Integer, Queue<int[]>> nodesAtCol) {
        if (node != null) {
            nodesAtCol.putIfAbsent(col, new PriorityQueue<>((a, b) -> {
               if (a[0] != b[0]) {
                   return Integer.compare(a[0], b[0]);
               } else {
                   return Integer.compare(a[1], b[1]);
               }
            }));
            /** store the level info for ordering. ***/
            nodesAtCol.get(col).offer(new int[]{level, node.val});
            dfs(node.left, level + 1, col - 1, nodesAtCol);
            dfs(node.right,level + 1, col + 1, nodesAtCol);
        }
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.mkTree("[3,1,4,0,2,2]");
        VerticalOrderTraversalOfBinaryTree sol = new VerticalOrderTraversalOfBinaryTree(); 
        System.out.println(sol.verticalTraversal(root));

        root = TreeNode.mkTree("[1,2,3,4,6,5,7]");
        System.out.println(sol.verticalTraversal(root));
    }
}
