//Leetcode 300 -- DP
import java.util.*;

public class LongestIncreasingSubseq {

    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        } 
        int[] dp = new int[nums.length]; //dp[i] meansing the lenghth of LIS ending at index i.
        Arrays.fill(dp, 1);
        int res = 1;
        
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

}
