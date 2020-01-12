//DP. Leetcode #53
public class MaxSubarray {

    public int maxSubArray(int[] nums) {
        int res = 0;
        if (nums == null || nums.length < 1) {
            return res;
        } else {
            int[] dp = new int[nums.length]; 
            dp[0] = nums[0];
            res = dp[0];
            for (int i = 1; i < nums.length; i++) {
                dp[i] = Math.max(0, dp[i - 1]) + nums[i];
                res = Math.max(dp[i], res);
            }
            return res;
        }
    }


    public static void main(String[] args) {
        int[] nums = new int[]{-2, 1,-3, 4, -1, 2, 1, -5, 4};
        MaxSubarray ms = new MaxSubarray();
        System.out.println(ms.maxSubArray(nums));
    }
}

