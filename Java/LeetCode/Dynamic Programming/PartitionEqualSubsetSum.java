/*** LC 416 --- DP, Knapsack ***/
import java.util.*;
public class PartitionEqualSubsetSum {

    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return false;
        } 

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) {
            return false;
        } else {
            int target = sum / 2;
            boolean[][] dp = new boolean[nums.length][target + 1];
            /** dp[j][target] represents whether the sums of the subsets of 
             * nums[0:j] contains target
            **/
            for (int i = 0; i < dp.length; i++) {
                dp[i][0] = true;
            } 

            /** the sums of subsets nums[0:i] can contain nums[i] if it's in range **/
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= target) {
                    dp[i][nums[i]] = true;     
                }
            }
            for (int i = 1; i < dp.length; i++) {
                int num = nums[i];
                for (int j = 1; j <= target; j++) {
                    if (j - num >= 0) {
                        /**Pick the num or not**/
                        dp[i][j] = dp[i - 1][j - num] || dp[i - 1][j]; 
                        if (dp[i - 1][j - num]) {
                            dp[i][j - num] = true;
                        }
                    }
                }
            }
            return dp[nums.length - 1][target];
        }
    }

    public static void main(String[] args) {
        PartitionEqualSubsetSum sol = new PartitionEqualSubsetSum();
        int[] nums = new int[]{1, 5, 11, 5};
        System.out.println(sol.canPartition(nums));

        nums = new int[]{3, 5, 2, 3, 5};
        System.out.println(sol.canPartition(nums));

        nums = new int[]{3,3,3,4,5};
        System.out.println(sol.canPartition(nums));
        nums = new int[]{9,1,2,4,10};
        System.out.println(sol.canPartition(nums));
        nums = new int[]{1,5,10,6};
        System.out.println(sol.canPartition(nums));
        nums = new int[]{14,9,8,4,3,2};
        System.out.println(sol.canPartition(nums));

    }
}
