/*** LC 560 --- Prefix Sum, Hashtable (Two Sum) ***/
import java.util.*;
public class SubarraySumEqualsK {

    /** the very brute force is O(n^3)
     * use prefixSum is O(n^2) ***/
    public int subarraySumO2(int[] nums, int k) {
        int n = nums.length;
        int res = 0;
        /** prefixSum[i] == sum of nums[0:i]***/
        int[] prefixSum = new int[n + 1];
        prefixSum[0] = 0;

        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        for (int subLen = 1; subLen <= n; subLen++) {
            /** the subarray is nums[i: i+subLen] **/
            for (int i = 0; i + subLen - 1 < n; i++) {
                /** the sum is prefixSum[i + subLen] 
                 * - prefixSum[i] **/
                int sum = prefixSum[i + subLen] - 
                    prefixSum[i];
                if (sum == k) {
                    res += 1;
                }
            }
        }
        return res;
    }

    /** Prefix Sum + HashMap 
     * sum[0,j] j is inclusive,
     *  sum[i,j] = sum[0, j] - sum[0, i - 1]
     *  for sum[0,j] we use two sum to check 
     *  whether k - sum[0, i - 1] was available.
     * **/
    public int subarraySum(int[] nums, int k) {

        /** store the current prefix Sum **/
        int sum = 0;
        /** record the frequency of a prefix Sum sum[0:i] **/
        Map<Integer, Integer> prefixSumFreq = new HashMap<>();
        int count = 0;
        for (int num : nums) {
            sum += num;
            /** corner case : sum[i:j] in which i = 0;**/
            if (sum == k) {
                count += 1;
            }
            /** check the frequency of the smaller prefix sum **/
            if (prefixSumFreq.containsKey(sum - k)) {
                count += prefixSumFreq.get(sum - k);
            } 
            prefixSumFreq.put(sum, 
                    prefixSumFreq.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
    

    public static void main(String[] args) {
        SubarraySumEqualsK sol = new SubarraySumEqualsK(); 
        int[] nums = new int[]{1,1,1};
        int k = 2;
        System.out.println(sol.subarraySum(nums, k));
        
        nums = new int[]{2};
        k = 2;
        System.out.println(sol.subarraySum(nums, k));
        
    }
}
