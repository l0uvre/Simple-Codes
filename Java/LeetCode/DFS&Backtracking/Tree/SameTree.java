/** LC 100 -- Binary Tree, DFS, Recursion. **/

public class SameTree {


    /** perform DFS on both binary trees, check if the subtrees of them are 
     * the same. **/
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null) {
            return q == null;
        } else if (q == null) {
            return p == null;
        }

        if (p.val != q.val) {
            return false;
        } else {
            // check if the subtrees are the same.
            return isSameTree(p.left, q.left) &&
                isSameTree(p.right, q.right);
        }
    }

}
