/** LC 83 -- Linked List, Two Pointers. **/
public class RemoveDuplicatesSortedList {
    /** use two pointers : prev, curr;
     * traverse the linked list, if prev.val == curr.val,
     * then we delete curr and let curr = prev.next. **/
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode prev = head;
        ListNode curr = head.next;
        while (curr != null) {
            if (prev.val == curr.val) {
                prev.next = curr.next;
                curr = prev.next;
            } else {
                prev = curr;
                curr = curr.next;
            }
        }
        return head;

    }
}
