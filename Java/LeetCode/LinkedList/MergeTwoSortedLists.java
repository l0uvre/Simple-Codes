/*** LC 21 --- Linked List, Divide and Conquer/Two Pointers --- ***/

class MergeTwoSortedLists {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        }
        ListNode head = (list1.val <= list2.val) ? list1: list2;
        if (list1.val > list2.val) {
            head.next = mergeTwoLists(list1, list2.next);
        } else {
            head.next = mergeTwoLists(list1.next, list2);
        }
        return head;
    }

    public ListNode mergeTwoListsIterative(ListNode list1, ListNode list2) {
        /** Dummy node added to the head of the merged list **/
        ListNode dummy = new ListNode(0, null);
        ListNode tail = dummy;
        while (list1 != null || list2 != null) {
            if (list1 == null) {
                tail.next = list2;
                break;
            } else if (list2 == null) {
                tail.next = list1;
                break;
            } else {
                if (list1.val <= list2.val) {
                    tail.next = list1;
                    tail = list1;
                    list1 = list1.next;
                } else {
                    tail.next = list2;
                    tail = list2;
                    list2 = list2.next;
                }
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        MergeTwoSortedLists sol = new MergeTwoSortedLists();
        sol.mergeTwoLists(ListNode.of(1,2,4), ListNode.of(1,3,4)).printList();
    }
}
