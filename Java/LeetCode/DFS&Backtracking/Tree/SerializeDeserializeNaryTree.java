/** LC 428 - Tree, DFS. **/ 

import java.util.*;
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
public class SerializeDeserializeNaryTree {
    private static final char DELIMITER = ',';
    // Encodes a tree to a single string.
    public String serialize(Node root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    /** serialize the N-ary tree into string "node numChildren childNode numGrandChild",
     * using dfs following the preorder; for the examp on Leetcode, 
     * it's "1,3,3,2,5,0,6,0,2,0,4,0". **/
    private void serialize(Node node, StringBuilder sb) {
        if (node == null) {
            return;
        } 
        sb.append(node.val);
        sb.append(DELIMITER);
        sb.append(node.children.size());
        sb.append(DELIMITER);
        for (Node child : node.children) {
            serialize(child, sb);
        }

    }
	
    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }
        String[] split = data.split(",");
        Queue<String> queue = new LinkedList<>(Arrays.asList(split));
        return deserialize(queue);
        
    }

    private Node deserialize(Queue<String> queue) {
        if (queue.isEmpty()) {
            return null;
        }

        Node node = new Node(Integer.valueOf(queue.poll()));
        int numChildren = Integer.valueOf(queue.poll());
        node.children = new ArrayList<>();
        for (int i = 0; i < numChildren; i++) {
            node.children.add(deserialize(queue));
        }
        return node;
    }
}
