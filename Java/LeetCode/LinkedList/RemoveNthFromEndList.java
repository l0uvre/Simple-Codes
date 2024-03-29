/** Leetcode 19 -- Linked List, Two Pointers --- **/
public class RemoveNthFromEndList {

    /**
     * use two pointers, one goes forward n steps ahead; and
     * then the other start moving with the faster pointer until
     * the faster one reach the end of the list; finally we can delete
     * the next node of the slower pointer. **/
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pointer = head;
        ListNode delayed = head;
        for (int i = 0; i < n; i++) {
            pointer = pointer.next;
        }
        /** if n == len(list), then remove the head 
         * and return. **/
        if (pointer == null) {
            head = head.next;
            return head;
        }
        /** use two pointers to get the previous node
         * of the node that will be removed. **/
        while (pointer.next != null) {
            pointer = pointer.next;
            delayed = delayed.next;
        }
        delayed.next = delayed.next.next;
        return head;
    }

    public static void main(String[] args) {
        ListNode list = ListNode.of(1, 2, 3, 4, 5);
        RemoveNthFromEndList tool = new RemoveNthFromEndList();
        list.printList();
        tool.removeNthFromEnd(list, 2).printList();

        list = ListNode.of(1, 2);
        tool.removeNthFromEnd(list, 2).printList();
    }


} 
