/*** LC 199 --  Tree, BFS, DFS***/
import java.util.*;

public class BinaryTreeRightSideView {
    /** BFS implementation ***/
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        List<TreeNode> layer = new ArrayList<>();
        layer.add(root);
        Queue<List<TreeNode>> q = new LinkedList<>();
        q.offer(layer);
        while (!q.isEmpty()) {
            layer = q.poll();
            res.add(layer.get(layer.size() - 1).val);
            List<TreeNode> nextLayer = new ArrayList<>();
            for (TreeNode node : layer) {
                if (node.left != null) {
                    nextLayer.add(node.left);
                }
                if (node.right != null) {
                    nextLayer.add(node.right);
                }
            }
            if (!nextLayer.isEmpty()) {
                q.offer(nextLayer);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        BinaryTreeRightSideView sol = new BinaryTreeRightSideView();  
        TreeNode root = TreeNode.mkTree("[1,2,3,null,5,null,4]");
        System.out.println(sol.rightSideView(root));

        root = TreeNode.mkTree("[1,null,5]");
        System.out.println(sol.rightSideView(root));

        root = TreeNode.mkTree("[1,2,3,null,5,null,4,null,6,null,null,7,8]");
        System.out.println(sol.rightSideView(root));

    }
}
