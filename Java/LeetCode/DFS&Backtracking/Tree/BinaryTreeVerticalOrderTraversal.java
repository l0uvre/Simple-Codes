/*** LC 314 --- Tree, DFS, BFS ***/
import java.util.*;
public class BinaryTreeVerticalOrderTraversal {

    /** using bfs, the level order traversal is from top to bottom
     * , left to right, which is perfect for this problem;
     * use a hashmap to store the nodes in the same column, key is
     * the index of column, root: 0 left: -1 right: +1. **/
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        /** store the nodes per colum, use treeMap to maintain the 
         * column index order **/
        Map<Integer, List<Integer>> nodesInCol = new TreeMap<>();
        bfs(root, nodesInCol);
        res.addAll(nodesInCol.values());
        return res;
    }

    /** store the column index of a TreeNode **/
    private class Node {
        TreeNode node;
        int col;
        Node(TreeNode n, int c) {
            node = n;
            col = c;
        }
    }

    /** level order traversal, we store extra col index for a treeNode **/
    private void bfs(TreeNode treeNode, Map<Integer, List<Integer>> map) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(treeNode, 0));
        while (!q.isEmpty()) {
            for (int i = q.size(); i > 0; i--) {
                Node n = q.poll();
                map.putIfAbsent(n.col, new LinkedList<>());
                map.get(n.col).add(n.node.val);
                if (n.node.left != null) {
                    q.offer(new Node(n.node.left, n.col - 1));
                }
                if (n.node.right != null) {
                    q.offer(new Node(n.node.right, n.col + 1));
                }
            }
        }
    }

    /** store the insert order of nodes in the tree **/
    private int insertIndex;
    /*** using dfs inorder traversal, we need to store level and
     * insert index to break the  tie, building a priority queue
     * for nodes in the same column;
     *  bfs is more straightforword, then, level by level**/
    public List<List<Integer>> verticalOrderDFS(TreeNode root) {
        /*** store the nodes per column, the root index is 0,
         * left -= 1, right += 1 **/
        List<List<Integer>> res = new LinkedList<>();
        /** store the nodes per column **/
        Map<Integer, Queue<int[]>> nodesAtCol = new TreeMap<>();
        insertIndex = 0;
        dfs(root, 0, 0, nodesAtCol);

        for (int i : nodesAtCol.keySet()) {
            List<Integer> col = new ArrayList<>();
            Queue<int[]> nodesAtColI = nodesAtCol.get(i);
            while (!nodesAtColI.isEmpty()) {
                col.add(nodesAtColI.poll()[2]);
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
                    /** low level nodes come first **/
                    return Integer.compare(a[0], b[0]);
                } else {
                    /*** if the nodes are in the same row and 
                     * column, arrange them in the order of insertion. **/
                    return Integer.compare(a[1], b[1]);
                }
            }));
            nodesAtCol.get(col).offer(new int[]{level, insertIndex, node.val});
            insertIndex += 1;
            dfs(node.left, level + 1, col - 1, nodesAtCol);
            dfs(node.right,level + 1, col + 1, nodesAtCol);
        }
    }

    public static void main(String[] args) {
        BinaryTreeVerticalOrderTraversal sol = new BinaryTreeVerticalOrderTraversal(); 
        TreeNode root = TreeNode.mkTree("[3,9,20,null,null,15,7]");
        System.out.println(sol.verticalOrder(root));

        root = TreeNode.mkTree("[3,9,8,4,0,1,7]");
        System.out.println(sol.verticalOrder(root));

        root = TreeNode.mkTree("[3,9,8,4,0,1,7,null,null,null,2,5]");
        System.out.println(sol.verticalOrder(root));

        root = TreeNode.mkTree("[1,2,3,4,5,6,null,null,7,8,null,null,9,null,10,null,11,10]");
        System.out.println(sol.verticalOrder(root));
    }
}
