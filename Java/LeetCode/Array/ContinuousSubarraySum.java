/*** LC 523 Similar to 560 --- Array, HashTable ***/
import java.util.*;
public class ContinuousSubarraySum {
    /** calcultate the prefix sums,
     * and store the <(prefixSum % k), index> to a hashmap;
     * if we got the same key like before, then sum[i : j] % k == 0,
     * sum[0:i] % k == s and sum[0:j] % k == s then sum[i+1:j] % k == 0,
     * we got an answer if j - i >= 2. **/
    public boolean checkSubarraySum(int[] nums, int k) {
        /** key is the prefixsum % k,
         * value is the last index for the prefx. **/
        Map<Integer, Integer> map = new HashMap<>();
        int currSum = 0;
        for (int i = 0; i < nums.length; i++) {
            currSum += nums[i];
            int mod = currSum % k;
            /** if the prefix sum is an answer. **/
            if (mod == 0 && i >= 1) {
                return true;
            } 
            if (map.containsKey(mod)) {
                if (i - map.get(mod) > 1) {
                    return true;
                }
            } else {
                map.put(mod, i);
            }
        }
        return false;     
    }

    public static void main(String[] args) {
        ContinuousSubarraySum sol = new ContinuousSubarraySum(); 
        int[] nums = new int[]{23,2,4,6,6};
        int k = 7;
        System.out.println(sol.checkSubarraySum(nums, k));

        nums = new int[]{5,0,0};
        k = 7;
        System.out.println(sol.checkSubarraySum(nums, k));

    }
}
