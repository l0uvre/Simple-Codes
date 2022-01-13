/** LC 503 -- Stack, Monotonic Stack. **/
import java.util.*;
public class NextGreaterElementII {
    /** Like we did in LC 496, but this time the nums 
     * is circular so we do two passes. **/
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < n * 2; i++) {
            int num = nums[i % n];
            while (!stack.isEmpty() && nums[stack.peek()] < num) {
                res[stack.pop()] = num;
            }
            if (i < n) {
                stack.push(i);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        NextGreaterElementII sol = new NextGreaterElementII(); 
        int[] nums = new int[]{1, 2, 1};
        System.out.println(Arrays.toString(sol.nextGreaterElements(nums)));

        nums = new int[]{1, 2, 3, 4, 3};
        System.out.println(Arrays.toString(sol.nextGreaterElements(nums)));

    }
}
