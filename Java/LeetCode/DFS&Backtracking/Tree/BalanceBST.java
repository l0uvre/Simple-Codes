/** LC 1382 -- Tree, DFS, BFS, Divide and Conquer. **/
public class BalanceBST {
    /** convert the BST to a sorted array using an 
     * in-order traversal and then construct a balanced
     * BST from the sorted array recursively. **/
    public TreeNode balanceBST(TreeNode root) {
        List<TreeNode> inorder = new ArrayList<>();
        dfs(root, inorder);
        return buildBalanced(inorder, 0, inorder.size() - 1);
    }

    /** build the blanced BST based off the sorted in-order
     * traversal in a way of Divide and Conquer. **/
    private TreeNode buildBalanced(List<TreeNode> list, int left,
            int right) {
        if (left > right) {
            return null;
        } else if (left == right) {
            TreeNode res = list.get(left);
            /** assign the pointers to null of leaf nodes. **/
            res.left = res.right = null;
            return res;
        } else {
            int mid = left + (right - left) / 2; 
            TreeNode root = list.get(mid);
            root.left = buildBalanced(list, left, mid - 1);
            root.right = buildBalanced(list, mid + 1, right);
            return root;
        }
    }

    private void dfs(TreeNode node, List<TreeNode> inorder) {
        if (node == null) {
            return;
        }
        dfs(node.left, inorder);
        inorder.add(node);
        dfs(node.right, inorder);
    }

}
