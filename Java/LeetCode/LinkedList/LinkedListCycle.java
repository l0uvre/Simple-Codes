public class LinkedListCycle {

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }        
        ListNode sptr = head;
        ListNode fptr = head;
        while (fptr != null) {
            fptr = fptr.next.next;
            sptr = sptr.next;
            if (fptr == sptr) {
                return true;
            } else if (fptr == null || fptr.next == null) {
                return false; 
            }
        }
        return false;
    }
}
