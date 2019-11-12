public class PalindromeLinkedList {
    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode fptr = head;
        ListNode sptr = head;
        while (fptr != null && fptr.next != null) {
            fptr = fptr.next.next;
            sptr = sptr.next;
        }
        if (fptr != null) {
            sptr = sptr.next;
        }
        sptr = reverse(sptr);
        fptr = head;
        while (sptr != null) {
            if (sptr.val != fptr.val) {
                return false;
            } 
            sptr = sptr.next;
            fptr = fptr.next;
        }
        return true;
    }
    
    private ListNode reverse(ListNode head) {
        ListNode prev = null, curr = head; 
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

}
