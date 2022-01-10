/** LC 708 -- LinkedList . **/
public class InsertSortedCircularLinkedList {
    /** see comments, really weird problem for me. **/
    public Node insert(Node head, int insertVal) {
        /** the linked list is null. **/
        if (head == null) {
            head = new Node(insertVal);
            head.next = head;
            return head;
        }

        Node curr = head;
        while (curr.next != head) {
            if (curr.val <= insertVal &&
                    curr.next.val >= insertVal) {
                break;
            }

            /** curr is the maximum node. **/
            if (curr.next.val < curr.val) {
                /** insertVal is larger than the maximum. **/
                if (insertVal > curr.val) {
                    break;
                }
                /** insertVal is smaller than the minimum. **/
                if (insertVal < curr.next.val) {
                    break;
                }
            }

            curr = curr.next;
        }

        /** the last step for above cases + 
         * the traversal ends normally.  **/
        Node newNode = new Node(insertVal);
        newNode.next = curr.next;
        curr.next = newNode;
        return head;
    }
}
