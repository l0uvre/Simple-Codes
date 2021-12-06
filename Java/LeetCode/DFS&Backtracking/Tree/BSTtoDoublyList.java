public class BSTtoDoublyList {
    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    /** Build a list which stores the nodes in
     * BST in order, it's DFS in order traversal.
     * O(N) **/
    public Node treeToDoublyList(Node root) {
        List<Node> list = new LinkedList<>();
        inorderTraverse(root, list);
        if (list.isEmpty()) {
            return null;
        } else {
            /** link the nodes in the doubly linked
             * list **/
            Node head = list.get(0);
            Node prev = list.get(list.size() - 1);
            for (Node curr : list) {
                curr.left = prev;
                prev.right = curr;
                prev = curr;
            }
            return head;
        }
        
    }

    /*** inorder dfs to build a linked list ***/
    private void inorderTraverse(Node node, List<Node> list) {
        if (node == null) {
            return;
        } else {
            inorderTraverse(node.left, list);
            list.add(node);
            inorderTraverse(node.right, list);
        }

    }
}
