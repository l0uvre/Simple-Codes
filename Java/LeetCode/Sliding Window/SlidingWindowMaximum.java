/*** LC 239  --- Sliding Window, Deque ---***/
import java.util.*;
public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        /** Use deque to store the promising items' indexes **/
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            /** if the index is out of range **/
            if (!deque.isEmpty() && deque.peek() < i - k + 1) {
                deque.poll();
            }
            /** Pop up those items that can not be the max **/
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            deque.offer(i); 
            /** we make sure that the head of the deque is the max **/
            if (i - k + 1 >= 0) {
                res[i - k + 1] = nums[deque.peek()];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        SlidingWindowMaximum sol = new SlidingWindowMaximum();
        int[] nums = new int[]{1,3,-1,-3,5,3,6,7};
        int k = 3;
        System.out.println(Arrays.toString(sol.maxSlidingWindow(nums, k)));

        nums = new int[]{1};
        k = 1;
        System.out.println(Arrays.toString(sol.maxSlidingWindow(nums, k)));

        
    }
}
