/** LC 515 -- Tree, DFS, BFS. **/
public class LargestValEachTreeRow {

    /** Do a DFS on the tree with a depth variable;
     * For every node, if the depth is larger than the size
     * of the result list, insert the value; if the depth is smaller,
     * then update the list if the node value is larger 
     * than list[depth]. **/
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, 0, res);
        return res;
    }

    private void dfs(TreeNode node, int depth, List<Integer> res) {
        if (node == null) {
            return;
        }

        if (depth >= res.size()) {
            res.add(node.val);
        } else {
            if (node.val > res.get(depth)) {
                res.set(depth, node.val);
            }
        }

        dfs(node.left, depth + 1, res);
        dfs(node.right, depth + 1, res);
    }
}
