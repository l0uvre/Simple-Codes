class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
    public static ListNode of(int... args) {
      ListNode result, p;
      if (args.length > 0) {
        result = new ListNode(args[0]);
      } else {
        return null;
      }
      p = result;
      for (int i = 1; i < args.length; i++) {
        p.next = new ListNode(args[i]);
        p = p.next;
      }
      return result;
    }
    
    public void printList() {
      ListNode p = this;
      while (p != null) {
        System.out.print(p.val + " ");
        p = p.next;
      }
      System.out.println();
    }
}

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
        ListNode endOfReversed = head.next;
        ListNode rest = reverseListRecur(head.next);
        endOfReversed.next = head;
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
