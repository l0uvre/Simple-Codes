// Leetcode 153 Binary Search 
public class FindMinRotated {
    /** More consice way Nov. 9 2021
     * Updated on Nov. 30
     */
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        int mid = 0;
        while (left < right) {
            mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
            
        }
        return nums[left];
    }
 
    
    public static void main(String[] args) {
        FindMinRotated fmr = new FindMinRotated();
        int[] nums = new int[]{3,4,5,1,2};
        System.out.println(fmr.findMin(nums));
        nums = new int[]{4,5,6,7,0,1,2};
        System.out.println(fmr.findMin(nums));
        nums = new int[]{2, 1};
        System.out.println(fmr.findMin(nums));
        nums = new int[]{1, 2};
        System.out.println(fmr.findMin(nums));
    }
}
