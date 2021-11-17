// LC 230 DFS, Tree
import java.util.ArrayList;

class KthSmallestInBST {
    public int kthSmallest(TreeNode root, int k) {
        ArrayList<Integer> sortedNodes = new ArrayList<>();
        dfs(root, sortedNodes);
        return sortedNodes.get(k - 1); 
    }

    private void dfs(TreeNode node, List<Integer> nodes) {
        if (node == null) {
            return;
        }
        dfs(node.left, nodes);
        nodes.add(node.val);
        dfs(node.right, nodes);

    }
}
