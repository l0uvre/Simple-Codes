public class ListNode {
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

