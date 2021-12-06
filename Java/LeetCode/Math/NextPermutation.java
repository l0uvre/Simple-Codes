/** LC 31 -- Math ***/
import java.util.*;
public class NextPermutation {
    /*** 1. find the rightmost increasing pair (i, i+1) 
     * which means nums right to the pair nums[i+1 :] is in
     * descending order; 
     *
     * 2. find the rightmost num in nums[i+1:] that is
     * larger than nums[i], swap
     *
     * 3. reverse the nums[i+1:] so the permutation
     * is as small as possible. ***/
    public void nextPermutation(int[] nums) {
        if (nums.length > 1) {
            int i = indexOfRightmostIncreasingPair(nums);
            /** it's the last permutation, reverse **/
            if (i == -1) {
                reverse(nums, 0);
            } else {
                int j = rightmostLargerthan(nums, i);
                swap(nums, i, j);
                /** reverse nums[i+1 :] **/
                reverse(nums, i + 1);
            }
        }
    }
    
    /** find the starting index of the rightmost increasing pair ***/
    private int indexOfRightmostIncreasingPair(int[] nums) {
        int res = -1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] < nums[i + 1]) {
                res = Math.max(res, i);
            }
        }
        return res;
    }

    /** find the rightmost index in nums[i+1, :] of the rightmost larger than nums[i] **/
    private int rightmostLargerthan(int[] nums, int i) {
        int index = i + 1;
        for (int j = i + 1; j < nums.length; j++) {
            if (nums[j] > nums[i]) {
                index = j;
            }
        }
        return index;
    }


    /** reverse nums[i:] **/
    private void reverse(int[] nums, int i) {
        int left = i;
        int right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


    public static void main(String[] args) {
        NextPermutation sol = new NextPermutation(); 
        int[] nums = new int[]{1,2,3};
        sol.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));

        nums= new int[]{3,2,1};
        sol.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));

        nums= new int[]{3,1,4,5,8,2,9,0};
        sol.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{2,3,6,5,4,1};
        sol.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

}
