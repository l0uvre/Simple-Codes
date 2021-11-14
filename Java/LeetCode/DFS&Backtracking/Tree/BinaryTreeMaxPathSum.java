// LC 124 --- DFS, DP Similar Problems: LC543, LC687
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
public class BinaryTreeMaxPathSum {


    int maxValue;

    public int maxPathSum(TreeNode root) {
        maxValue = Integer.MIN_VALUE;
        maxGain(root);
        return maxValue;
    }

    /**
     *
     * For a specific node, calculate the maximum gain it can get from its sub branches 
     * It's easier to think recursively and from bottom to up.
     */
    private int maxGain(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            int leftMaxGain = Math.max(maxGain(node.left), 0);
            int rightMaxGain = Math.max(maxGain(node.right), 0);
            int currPathSum = leftMaxGain + rightMaxGain + node.val;
            maxValue = Math.max(currPathSum, maxValue);
            return Math.max(leftMaxGain + node.val, rightMaxGain + node.val);
        }   

    }

}
