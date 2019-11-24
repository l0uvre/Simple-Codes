// Leetcode 112
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
}

public class PathSum {
    public boolean hasPathSum(TreeNode root, int sum) {
        return dfs(root, 0, sum);
    }
    private boolean dfs(TreeNode curr, int tmp, int sum) {
        if (curr == null) {
            return false;
        } 
        
        if (curr.left == null && curr.right == null) {
            return tmp + curr.val == sum;
        }
        if (dfs(curr.left, tmp + curr.val, sum)) {
            return true;
        }
        if (dfs(curr.right, tmp + curr.val, sum)) {
            return true;
        }
        return false;
    }
}
