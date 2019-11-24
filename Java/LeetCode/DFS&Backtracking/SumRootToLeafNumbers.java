// Leetcode 129
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
}
public class SumRootToLeafNumbers {
    public int sumNumbers(TreeNode root) {
        return dfs(root, "", 0); 
    }

    private int dfs(TreeNode curr, String num, int sum) {
        if (curr == null) {
            return sum;
        }
        if (curr.left == null && curr.right == null) {
            return sum + Integer.parseInt(num + curr.val);
        }
        sum = dfs(curr.left, num + curr.val, sum);
        sum = dfs(curr.right, num + curr.val, sum);
        return sum;
    }
}
