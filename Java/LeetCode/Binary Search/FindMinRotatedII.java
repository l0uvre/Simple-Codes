// Leetcode 154 Binary Search 
public class FindMinRotatedII {
    /***
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        } else {
            int start = 0, end = nums.length - 1;
            int mid = -1;
            while (start <= end) {
                mid = start + (end - start) / 2;
                if (start == mid) {
                    mid = nums[start] > nums[end] ? end : start;
                    break;
                }
                if (nums[mid] < nums[start]) {
                    end = mid;
                } else if (nums[mid] > nums[start]) {
                    if (nums[start] > nums[end]) {
                        start = mid + 1; 
                    } else if (nums[start] < nums[end]) {
                        end = mid;
                    } else {
                        start += 1;
                    }
                } else {
                    start += 1;
                }
            
            }
            return nums[mid];
        }
    }
    **/

    /**
     * On Nov. 9 2021 more concise.
     */
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else if (nums[mid] == nums[right]) {
                if (nums[left] < nums[right]) {
                    right = mid - 1;
                } else if (nums[left] > nums[right]) {
                    right = mid;
                } else {
                    right--; //skip the duplicate
                }
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
