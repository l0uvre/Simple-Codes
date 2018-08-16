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

class MergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }
        int val;
        val = Math.min(l1.val, l2.val);
        ListNode head = new ListNode(val);
        if (l1.val > l2.val) {
            head.next = mergeTwoLists(l1, l2.next);
        } else {
            head.next = mergeTwoLists(l1.next, l2);
        }
        return head;
    }

    public static void main(String[] args) {
        MergeTwoSortedLists sol = new MergeTwoSortedLists();
        sol.mergeTwoLists(ListNode.of(1,2,4), ListNode.of(1,3,4)).printList();
    
    }
}
