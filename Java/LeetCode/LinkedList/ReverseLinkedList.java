
public class ReverseLinkedList {

    public ListNode reverseList(ListNode head) {
        ListNode curr = head , prev = null;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public ListNode reverseListRecur(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode endofreversed = head.next;
        ListNode rest = reverseListRecur(head.next);
        endofreversed.next = head;
        head.next = null;//important
        return rest;
    }

    public static void main(String[] args) {
        ListNode list = ListNode.of(1, 2, 3, 4, 5);
        list.printList();
        ReverseLinkedList rll = new ReverseLinkedList();
        list = rll.reverseList(list);
        list.printList();
        list = rll.reverseListRecur(list);
        list.printList();
    }
} 
