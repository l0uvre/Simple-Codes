/** LC 138 --- LinkedList, HashTable **/
public class CopyListRandomPointer {
    /** use hashmap to maintain a pointer
     * from the oldNode to the newNode. **/
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        if (head =- null) {
            return null;
        }
        /** use a hashtable to store the reference for new nodes,
         * <oldNode, newNode> the old referenct -> new reference;
         *the default hashcode() is unique for different instance. **/
        Map<Node, Node> map = new HashMap<>();
        Node p = head;

        while (p != null) {
            map.put(p, new Node(p.val));
            p = p.next;
        }
        p = head;
        while (p != null) {
            Node copied = map.get(p);
            copied.next = map.get(p.next);
            copied.random = map.get(p.random);
            p = p.next;
        }
        return map.get(head);
    }

}
