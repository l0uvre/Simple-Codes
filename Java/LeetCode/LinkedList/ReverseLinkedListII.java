public class ReverseLinkedListII {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode curr = head, prev = null;
        int i = 0;
        while (curr != null) {
            i++;
            if (i < m || i > n) {
                prev = curr;
                curr = curr.next;
            } else {
                ListNode prevEnd = prev;
                ListNode endofReversed = curr;
                prev = null;
                while (i <= n) {
                    ListNode next = curr.next;
                    curr.next = prev;
                    prev = curr;
                    curr = next;
                    i++;
                }
                //prev.printList();
                //System.out.println(i);
                if (prevEnd != null) {
                    prevEnd.next = prev;
                } else {
                    head = prev; 
                }
                endofReversed.next = curr;
                prev = endofReversed;
                i--;
            }
        }
       
        return head;
    }

    public static void main(String[] args) {
        ListNode list = ListNode.of(1, 2, 3, 4, 5);
        ReverseLinkedListII rll = new ReverseLinkedListII();
        list.printList();
        list = rll.reverseBetween(list, 2, 4);
        list.printList();

        System.out.println();
        list = ListNode.of(1, 2, 3, 4, 5);
        list.printList();
        list = rll.reverseBetween(list, 5, 5);
        list.printList();

        System.out.println();
        list = ListNode.of(3, 5);
        list.printList();
        list = rll.reverseBetween(list, 1, 1);
        list.printList();

        System.out.println();
        list = ListNode.of(3, 5);
        list.printList();
        list = rll.reverseBetween(list, 1, 2);
        list.printList();

    }
}
