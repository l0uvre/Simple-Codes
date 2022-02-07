/** LC 366 -- Tree, DFS */


import java.util.*;
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
public class LeavesBinaryTree {

    /** perform a dfs from root, calculate the height of every node when 
    it was treated as the root of its subtree; take the larger value as its height;
    
    leaves are of subtrees of height one;

    Along the way of dfs, insert the node into the array list with its height as the index.
       */
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> leaves = new ArrayList<>();
        dfs(leaves, root);
        return leaves;
    }

    private int dfs(List<List<Integer>> leaves, TreeNode node) {
        if (node == null) {
            return -1;
        } 
        int leftHeight = dfs(leaves, node.left);
        int rightHeight = dfs(leaves, node.right);

        int maxHeight = Math.max(leftHeight, rightHeight) + 1;
        if (leaves.size() <= maxHeight) {
            leaves.add(new ArrayList<>());
            
        }
        leaves.get(maxHeight).add(node.val);
        node.left = null;
        node.right = null;
        return maxHeight;

    }
}

