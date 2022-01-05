/** LC 16 -- Two Pointers. **/
import java.util.*;
public class ThreeSumClosest {
    /** pick an item from the nums, and use a two pointer scheme
     * to update the result. **/
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res = nums[0] + nums[1] + nums[2];
        for (int i = 0; i + 2 < nums.length; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum < target) {
                    left++;
                } else if (sum > target) {
                    right--;
                } else {
                    return sum;
                }

                if (Math.abs(res - target) > Math.abs(sum - target)) {
                    res = sum;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-1, 2, 1, -4};
        int target = 1;
        ThreeSumClosest sol = new ThreeSumClosest(); 
        System.out.println(sol.threeSumClosest(nums, target));

        nums = new int[]{0, 0, 0};
        target = 1;
        System.out.println(sol.threeSumClosest(nums, target));
    }
}
