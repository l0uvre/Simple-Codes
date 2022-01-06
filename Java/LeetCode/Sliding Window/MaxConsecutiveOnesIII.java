/** LC 1004 -- Sliding Window. **/
public class MaxConsecutiveOnesIII {
    /** Use a sliding window to expand it with at most
     * k zeros; shrink it when there is more than k zeros. **/
    public int longestOnes(int[] nums, int k) {
        int left = 0;
        int numOfZeros = 0;
        int res = 0;
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                numOfZeros += 1;
                while (numOfZeros > k) {
                    if (nums[left] == 0) {
                        numOfZeros -= 1;
                    }
                    left++;
                }
            }
            res = Math.max(res, right - left + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        MaxConsecutiveOnesIII sol = new MaxConsecutiveOnesIII(); 
        int[] nums = new int[]{1,1,1,0,0,0,1,1,1,1,0};
        int k = 2;
        System.out.println(sol.longestOnes(nums, k));

        nums = new int[]{0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
        k = 3;
        System.out.println(sol.longestOnes(nums, k));

        nums = new int[]{0};
        k = 0;
        System.out.println(sol.longestOnes(nums, k));

        nums = new int[]{1};
        k = 0;
        System.out.println(sol.longestOnes(nums, k));
    }
}
