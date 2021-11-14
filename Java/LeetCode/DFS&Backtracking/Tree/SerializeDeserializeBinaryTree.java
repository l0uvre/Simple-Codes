// LC 297 -- Design, BFS, DFS, Tree
import java.util.*;

public class SerializeDeserializeBinaryTree {
    private static final String DELIMITER = ",";
    private static final String NULLSTRING= "N";

    // Encodes a tree into a single string. Preorder traversal using iterative version.
    public String serialize2(TreeNode root) {
        List<String> nodes = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        Stack<TreeNode> s = new Stack<>();
        s.push(root);
        while (s.size() > 0) {
            TreeNode curr = s.pop();
            if (curr == null) {
                sb.append(NULLSTRING);
            } else {
                sb.append(String.valueOf(curr.val));
                s.push(curr.right);
                s.push(curr.left);
            }
            sb.append(DELIMITER);
        }

        sb.setLength(sb.length() - 1); // remove the last comma
        return sb.toString();
    }


    // Encodes a tree into a single string. Preorder traversal. recursive version.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    private void buildString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(NULLSTRING);
            sb.append(DELIMITER);
        } else {
            sb.append(String.valueOf(node.val));
            sb.append(DELIMITER);
            buildString(node.left, sb);
            buildString(node.right, sb);
        }
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] strs = data.split(DELIMITER);
        Queue<String> nodes = new LinkedList<>();
        nodes.addAll(Arrays.asList(strs));
        return buildTree(nodes);

    }

    // preorder traversal to build the tree.
    private TreeNode buildTree(Queue<String> nodes) {
        if (nodes.isEmpty()) {
            return null;
        }
        String val = nodes.poll();
        if (val.equals(NULLSTRING)) {
            return null;
        } else {
            TreeNode node = new TreeNode(Integer.valueOf(val));
            node.left = buildTree(nodes);
            node.right = buildTree(nodes);
            return node;
        }
    }

}
