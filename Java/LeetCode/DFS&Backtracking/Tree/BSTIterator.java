/** LC 173 -- Tree, DFS, Stack. **/
import java.util.*;

public class BSTIterator {

    /** Use stack, push all the left nodes of the root 
     * recursivly into the stack; every time next() is called,
     * pop the top off, and treat the right node as root.
     **/

    private Deque<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        stack = new LinkedList<>();
        pushLeftAll(root);
    }

    private void pushLeftAll(TreeNode root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }
    
    public int next() {
        TreeNode next = stack.pop();
        pushLeftAll(next.right);
        return next.val;
    }
    
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /*** Do an inorder traversal and 
     * save the result to a list, use a pointer to get the
     * next value, O(N) space, O(1) time
     *
    private List<Integer> inorder;
    private int index;

    private void inOrderTraverse(TreeNode root, List<Integer> inorder) {
        if (root == null) {
            return;
        } else {
            inOrderTraverse(root.left, inorder);
            inorder.add(root.val);
            inOrderTraverse(root.right, inorder);
        }
    }

    public BSTIterator(TreeNode root) {
        inorder = new ArrayList<>();
        inOrderTraverse(root, inorder);
        index = 0;
    }
    
    public int next() {
        int res = inorder.get(index);
        index += 1;
        return res;
    }
    
    public boolean hasNext() {
        return index < inorder.size();
    }**/
}
