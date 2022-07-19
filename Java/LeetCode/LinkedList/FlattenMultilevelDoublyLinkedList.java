/** LC 430 -- Linked List. **/
/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/
public class FlattenMultilevelDoublyLinkedList {
    /** traverse the linked list with a pointer; if we have a node with
     * child, then we traverse the child list and add it into 
     * our original linked list, and move the pointer to the next. **/
    public Node flatten(Node head) {
        Node p = head;
        while (p != null) {
            if (p.child != null) {
                Node tmp = p.child;
                while (tmp.next != null) {
                    tmp = tmp.next;
                }
                Node next = p.next;
                /** add the head of the child linked list to the original. **/
                p.next = p.child;
                p.child.prev = p;
                tmp.next = next;
                if (next != null) {
                    next.prev = tmp;
                }
                p.child = null;
            }
            p = p.next;
        }
        return head;
    }
}
