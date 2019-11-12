import java.util.*;

public class ZigzagTraversal {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean leftToRight = true;
        while (!q.isEmpty()) {
            LinkedList<Integer> list = new LinkedList<>();
            for (int i = q.size(); i > 0; i--) {
                TreeNode curr = q.poll();
                if (leftToRight) {
                    list.add(curr.val);
                } else {
                    list.addFirst(curr.val);
                }

                if (curr.left != null) {
                    q.offer(curr.left);
                }
                if (curr.right != null) {
                    q.offer(curr.right);
                }
            }
            leftToRight = !leftToRight;
            res.add(list);
        }
        return res;
    }

}
