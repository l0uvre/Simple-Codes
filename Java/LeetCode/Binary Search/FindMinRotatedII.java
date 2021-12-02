// Leetcode 154 Binary Search 
public class FindMinRotatedII {
    /**
     * On Nov. 9 2021 more concise.
     * Updated on Nov. 30 2021
     */
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else if (nums[mid] == nums[right]) {
                right--;
            } else {
                right = mid;
            }

        }
        return nums[left];
    }
    

    public static void main(String[] args) {
        FindMinRotatedII fmri = new FindMinRotatedII();
        int[] nums = new int[]{1, 3, 5};
        System.out.println(fmri.findMin(nums));
        nums = new int[]{2, 2, 2, 0, 1};
        System.out.println(fmri.findMin(nums));
        nums = new int[]{3, 3, 1, 3};
        System.out.println(fmri.findMin(nums));
    
        nums = new int[]{3, 3, 3, 3};
        System.out.println(fmri.findMin(nums));
        
        nums = new int[]{3, 3};
        System.out.println(fmri.findMin(nums));
        nums = new int[]{1,1,3,1};
        System.out.println(fmri.findMin(nums));
    }

}
