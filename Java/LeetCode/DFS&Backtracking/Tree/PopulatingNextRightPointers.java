/** LC 116 -- Tree, DFS, BFS. **/
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/
public class PopulatingNextRightPointers {

    /** perform a DFS and connect next pointers on the fly. **/
    public Node connect(Node root) {
        connect(root, null);
        return root;
    }

    /** helper function to build the next pointer connections. **/
    private void connect(Node curr, Node next) {
        if (curr == null) {
            return;
        }
        curr.next = next;
        connect(curr.left, curr.right);
        if (next != null) {
            connect(curr.right, next.left);
        } else {
            connect(curr.right, null);
        }
    }

}
