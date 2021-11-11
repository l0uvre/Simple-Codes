// Leetcode 152 Maximum product subarray ---DP
public class MaxProductSubarray {
    public int maxProduct(int[] nums) {
        if (nums ==  null || nums.length == 0) {
            return 0;
        } else {
            int n = nums.length;
            int[] dpMax = new int[n]; // maxmimux product of subarrays ending at i for nums[0:i] 
            int[] dpMin = new int[n]; // minimum product of subarrays ending at i for nums[0:i] 
            int res = nums[0];
            dpMax[0] = nums[0];
            dpMin[0] = nums[0];

            for (int i = 1; i < n; i++) {
                if (nums[i] < 0) {
                    dpMin[i] = Math.min(dpMax[i - 1] * nums[i], nums[i]);
                    dpMax[i] = Math.max(dpMin[i - 1] * nums[i], nums[i]);
                } else {
                    dpMin[i] = Math.min(dpMin[i - 1] * nums[i], nums[i]);
                    dpMax[i] = Math.max(dpMax[i - 1] * nums[i], nums[i]);
                }
                res = Math.max(dpMax[i], res);
            }
            return res;
        }
    }
}
