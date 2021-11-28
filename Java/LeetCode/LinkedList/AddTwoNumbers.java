
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return addTwoNumbersHelper(l1, l2, 0);
    }
    
    private ListNode addTwoNumbersHelper(ListNode l1, ListNode l2, int carry) {
        if (l1 == null && l2 == null) {
            return carryOnList(null, carry);
        } else if (l1 == null) {
            if (carry == 1) {
                return carryOnList(l2, carry);
            } else {
                return l2;
            }
        } else if (l2 == null) {
            if (carry == 1) {
                return carryOnList(l1, carry);
            } else {
                return l1;
            }
        } else {
            int newCarry = l1.val + l2.val + carry >= 10 ? 1 : 0;
            ListNode head = new ListNode((l1.val + l2.val + carry) % 10);
            head.next = addTwoNumbersHelper(l1.next, l2.next, newCarry);
            return head;
        }
    }
    
    private ListNode carryOnList(ListNode l, int carry) {
        if (l == null) {
            if (carry == 1) {
                return new ListNode(carry);
            } else {
                return null;
            }
        }
        int newCarry = l.val + carry >= 10 ? 1 : 0;
        ListNode head = new ListNode((l.val + carry) % 10);
        head.next = carryOnList(l.next, newCarry);
        return head;
    }

    public static void main(String[] args) {
        ListNode l = ListNode.of(1,2,3,4);
        l.printList();
        AddTwoNumbers sol = new AddTwoNumbers();
        ListNode l1 = ListNode.of(2,4,3);
        ListNode l2 = ListNode.of(5,6,4);
        sol.addTwoNumbers(l1, l2).printList();
        l1 = ListNode.of(1);
        l2 = ListNode.of(9,9,9,9,9,9,9,9);
        sol.addTwoNumbers(l1, l2).printList();

    }
    
    
}
