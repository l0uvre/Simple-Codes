/** LC 15 -- Two Pointers. **/
import java.util.*;
public class ThreeSum {

    /** Sort the input array and skip the duplicates;
     * pick the first item then run through the remaining array
     * to do a two sum; in the meantime, skip the duplicates too. **/
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return res;
        } else {
            Arrays.sort(nums);
            for (int i = 0; i + 2 < nums.length; i++) {
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                int sum = -nums[i];
                int left = i + 1;
                int right = nums.length - 1;
                while (left < right) {
                    if (nums[left] + nums[right] < sum) {
                        left++;
                    } else if (nums[left] + nums[right] > sum) {
                        right--;
                    } else {
                        res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }

                        left++;
                        right--;
                    }
                }
            }
            return res;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-1,0,1,2,-1,-4};
        ThreeSum sol = new ThreeSum();
        System.out.println(sol.threeSum(nums));

        nums = new int[]{};
        System.out.println(sol.threeSum(nums));

        nums = new int[]{0};
        System.out.println(sol.threeSum(nums));

    }
}
