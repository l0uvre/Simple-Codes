/** LC 377 -- DP. **/
public class CombinationSumIV {

    /** create a dp[] that dp[i] represents the
     * number of combinations that sum up to i;
     *
     * base case: dp[0] = 1;
     *
     * dp[i] = sum(dp[i - nums[j]). **/
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];        
        dp[0] = 1;

        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i - nums[j] >= 0) {
                    dp[i] += dp[i - nums[j]];
                }
            }
        }
        return dp[target];
    }

}
