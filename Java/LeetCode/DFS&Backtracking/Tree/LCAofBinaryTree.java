/*** LC 236 Binary Tree, Recursion. ***/
import java.util.*;
public class LCAofBinaryTree {

     public TreeNode lowestCommonAncestorRecursive(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        } 
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        } else {
            return root;
        }
        
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pathToP = pathFromPtoQ(root, p);
        List<TreeNode> pathToQ = pathFromPtoQ(root, q);
        
        int i = 0;
        for (i = 0; i < pathToP.size() && i < pathToQ.size(); i++) {
            if (pathToP.get(i).val != pathToQ.get(i).val) {
                i--;
                break;
            }
        }
        return pathToP.get(i);
        
    }

    /** DFS way to build a path from Node P to Node Q */
    private List<TreeNode> pathFromPtoQ(TreeNode p, TreeNode q) {
        List<TreeNode> res = new ArrayList<>();
        Stack <List<TreeNode>> stack = new Stack<>();
        List<TreeNode> fringe = new ArrayList<>();
        fringe.add(p);
        stack.push(fringe);
        while (!stack.isEmpty()) {
            List<TreeNode> path = stack.pop();
            TreeNode curr = path.get(path.size() - 1);
            if (curr.val == q.val) {
                res = path;
                break;
            }
            if (curr.left != null) {
                List<TreeNode> newPath = new ArrayList<>(path);
                newPath.add(curr.left);
                stack.push(newPath);
            }
            if (curr.right != null) {
                List<TreeNode> newPath = new ArrayList<>(path);
                newPath.add(curr.right);
                stack.push(newPath);
            }
        }
        List<Integer> intPath = new ArrayList<>();
        for (TreeNode node : res) {
            intPath.add(node.val);
        }
        return res;
    }

    

}
