/*** LC 23 --- Linked List, Heap / Divide and Conquer ***/
import java.util.*;

public class MergeKSortedLists {

    public ListNode mergeKListsUsingHeap(ListNode[] lists) {
        /*** dummy node added to the head of the result **/
        ListNode dummy = new ListNode(0, null);
        ListNode tail = dummy;
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) ->
                Integer.compare(a.val, b.val));
        if (lists != null) {
            for (ListNode list : lists) {
                if (list != null) {
                    pq.offer(list);
                }
            }
        }
        while (!pq.isEmpty()) {
            ListNode curr = pq.poll();
            tail.next = curr;
            tail = curr;
            if (curr.next != null) {
                pq.offer(curr.next);
            }
        }
        return dummy.next;
    }
    
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
        ListNode head = (l1.val <= l2.val) ? l1 : l2;
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
