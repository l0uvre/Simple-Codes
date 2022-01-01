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
        return clone(node, created);
    }

    /** Clone nodes in the means of DFS. **/
    private Node clone(Node node, Map<Integer, Node> created) {
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
                Node next = clone(neighbor, created);
                curr.neighbors.add(next);
            }
            return curr;
        }
    } 

    /** Clone nodes in the means of BFS. **/
    private Node cloneGraphBFS(Node node, Map<Integer, Node> created) {
        if (node != null) {
            Queue<Node> q = new LinkedList<>();
            q.offer(node);
            created.put(node.val, new Node(node.val));

            while (!q.isEmpty()) {
                Node curr = q.poll();
                Node copy = created.get(curr.val);
                for (Node neighbor: curr.neighbors) {
                    if (!created.containsKey(neighbor.val)) {
                        Node copyOfNeighbor = new Node(neighbor.val);
                        copy.neighbors.add(copyOfNeighbor);
                        created.put(neighbor.val, copyOfNeighbor);
                        q.offer(neighbor);
                    } else {
                        copy.neighbors.add(created.get(neighbor.val));
                    }
                }
            }
            return created.get(node.val);
        } else {
            return null;
        }
    }
}
