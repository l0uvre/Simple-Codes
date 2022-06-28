/** LC 116 -- Tree, BFS. **/
import java.util.*;
public class PopulatingNextRightPointers {

    /** peform a BFS level by level, assign prev.next = curr. **/
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            Node prev = null;
            Node curr = null;
            for (int i = q.size(); i > 0; i--) {
                curr = q.poll();
                if (prev != null) {
                    prev.next = curr;
                }
                if (curr.left != null) {
                    q.offer(curr.left);
                }
                if (curr.right != null) {
                    q.offer(curr.right);
                }
                prev = curr;
            }
        }
        return root;
    }
}
