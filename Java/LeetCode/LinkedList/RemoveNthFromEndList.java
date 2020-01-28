// Leetcode 19, Linked List
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

public class RemoveNthFromEndList {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode ptr = head, delayed = head;
        for (int i = 0; i < n; i++) {
            ptr = ptr.next;
            if (ptr == null) {
                head = head.next;
                return head;
            }
        }

        while (ptr.next != null) {
            ptr = ptr.next;
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
