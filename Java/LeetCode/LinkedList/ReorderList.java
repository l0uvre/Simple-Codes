public class ReorderList {
    /**Approach : 
     * 1. find the middle node 
     * 2. reverse the right half list.
     * 3. merge the left and right half.
     * **/
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        } else {
            ListNode fast = head;
            ListNode slow = head;
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }

            /**We need to move one forward to get the right half. **/
            if (fast != null) {
                slow = slow.next;
            }

            /** reverse the right half **/
            ListNode prev = null;
            ListNode curr = slow;
            while (curr != null) {
                ListNode next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }

            /** merge them one by one **/
            ListNode dummy = new ListNode(0);
            ListNode tail = dummy;
            ListNode pointer1 = head;
            ListNode pointer2 = prev;


            while (pointer2 != null) {
                tail.next = pointer1;
                pointer1 = pointer1.next;

                tail.next.next = pointer2;
                tail = pointer2;
                pointer2 = pointer2.next;
            }

            /**If there is a node left in the left part. **/
            if (pointer1 != slow) {
                tail.next = pointer1;
                tail = pointer1;
                tail.next = null;
            }
            head = dummy.next;
        }
    }

    public static void main(String[] args) {
        ListNode head = ListNode.of(1, 2, 3, 4); 
        ReorderList sol = new ReorderList(); 
        sol.reorderList(head);
        head.printList();

        head = ListNode.of(1,2,3,4,5);
        sol.reorderList(head);
        head.printList();
    }

}
