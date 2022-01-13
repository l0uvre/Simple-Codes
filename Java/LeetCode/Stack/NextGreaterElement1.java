/** LC 496 -- Stack, Monotonic Stack, Hashtable. **/
import java.util.*;

public class NextGreaterElement1 {

    /** Use a hashmap<int, int> to store the num and its next greater
     * element; traverse the numbers in nums2 and use a monotonic stack
     * to push and pop nums; every time, we check the top of the stack,
     * if the top is smaller than the current number, we don't have a 
     * greater number so push it to the stack; otherwise, we pop the nums
     * off the stack and add the <num, currNum> to the hashmap until the top
     * of the stack is smaller. **/
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        /** num -> the next greater number **/
        Map<Integer, Integer> map = new HashMap<>();
        /** the monotonic stack. **/
        Deque<Integer> stack = new LinkedList<>();
        for (int num : nums2) {
            if (stack.isEmpty() || stack.peek() > num) {
                stack.push(num);
            } else {
                while (!stack.isEmpty() && stack.peek() < num) {
                    map.put(stack.pop(), num);
                }
                stack.push(num);
            }
        }
        for (int i = 0; i < res.length; i++) {
            res[i] = map.getOrDefault(nums1[i], -1);
        }
        return res;
    }

    public static void main(String[] args) {
        NextGreaterElement1 sol = new NextGreaterElement1(); 
        int[] nums1 = new int[]{4, 1, 2};
        int[] nums2 = new int[]{1, 3, 4, 2};
        System.out.println(Arrays.toString(sol.nextGreaterElement(nums1, nums2)));

        nums1 = new int[]{2, 4};
        nums2 = new int[]{1, 2, 3, 4};
        System.out.println(Arrays.toString(sol.nextGreaterElement(nums1, nums2)));
    }
}
