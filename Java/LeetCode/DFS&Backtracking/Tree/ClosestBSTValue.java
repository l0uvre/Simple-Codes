/** LC 270 -- DFS, Tree. **/
public class ClosestBSTValue {
    /** traverse the BST from the root;
     * if the target < root.val:
     *   then traverse the left sub tree;
     * else traverse the right sub tree;
     *
     * until we reach a leaf. **/
    public int closestValue(TreeNode root, double target) {
        int res = root.val;
        TreeNode node = root;
        while (node != null) {
            if (Math.abs(target - node.val) < Math.abs(
                    target - res)) {
                res = node.val;
            }
            if (target < node.val) {
                node = node.left;
            } else if (target > node.val) {
                node = node.right;
            } else {
                break;
            }
        }
        return res;
    }
}
