class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
}

public class ValidateBinarySearchTree {
    public boolean isValidBST(TreeNode root) {
        return isValidNode(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    private boolean isValidNode(TreeNode node, long min, long max) {
        if (node == null) {
            return true;
        }
        if (node.val > max || node.val < min) {
            return false;
        }
        //long because of overflow of integer.
        return isValidNode(node.left, min, (long)node.val - 1) && isValidNode(node.right, (long)node.val + 1, max);
    }
}
