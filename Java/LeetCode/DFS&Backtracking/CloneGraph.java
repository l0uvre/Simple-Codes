/**** LC 133 --- Graph, DFS ***/
import java.util.*;
public class CloneGraph {
    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public Node cloneGraph(Node node) {
        Map<Integer, Node> created = new HashMap<>();
        return cloneGraph(node, created);
    }

    private Node cloneGraph(Node node, Map<Integer, Node> created) {
        if (node == null) {
            return null;
        } else if (created.containsKey(node.val)) {
            /*** we need to avoid create new instances of nodes that we've 
             * created **/
            return created.get(node.val);
        } else {
            Node curr = new Node(node.val);
            created.put(curr.val, curr);
            for (Node neighbor : node.neighbors) {
                Node next = cloneGraph(neighbor, created);
                curr.neighbors.add(next);
            }
            return curr;
        }
    } 
}
