/** LC 724 -- Prefix Sum. **/
public class FindPivotIndex {
    /** for an index i, calcute the left sum and
     * the right sum using a prefixSum. **/
    public int pivotIndex(int[] nums) {
        int n = nums.length;
        int[] prefixSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = nums[i - 1] + prefixSum[i - 1];
        }

        for (int i = 0; i < n; i++) {
            int leftSum = prefixSum[i];
            int rightSum = prefixSum[n] - prefixSum[i + 1];
            if (leftSum == rightSum) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,7,3,6,5,6};
        FindPivotIndex sol = new FindPivotIndex(); 
        System.out.println(sol.pivotIndex(nums));

        nums = new int[]{1, 2, 3};
        System.out.println(sol.pivotIndex(nums));
    }
}
