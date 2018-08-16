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

public class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        } 
        return merge(lists, 0, lists.length - 1);
        
    }
    
    private ListNode merge(ListNode[] lists, int start, int end) {
        if (start > end) {
            return null;
        } else if (start == end) {
            return lists[start];
        } else {
            int mid = start + (end - start) / 2;
            ListNode prev = merge(lists, start, mid);
            ListNode post = merge(lists, mid + 1, end);
            return mergeTwoLists(prev, post);
            
        }
    }
    
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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
        MergeKSortedLists sol = new MergeKSortedLists();
        ListNode[] lists = {ListNode.of(1, 4, 5), ListNode.of(1, 3, 4), ListNode.of(2, 6)};
        sol.mergeKLists(lists).printList();
        ListNode[] lists2 = {ListNode.of(-2, -1, -1, -1), ListNode.of()};
        sol.mergeKLists(lists2).printList();
    }
}
