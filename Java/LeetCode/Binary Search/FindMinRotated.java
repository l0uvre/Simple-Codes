// Leetcode 153 Binary Search 
public class FindMinRotated {
    /**
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        } else {
            int start = 0, end = nums.length - 1;
            int mid = -1;
            while (start <= end) {
                mid = start + (end - start) / 2;
                if (nums[mid] > nums[start]) {
                    if (nums[start] < nums[end]) {
                        end = mid - 1;
                    } else {
                        start = mid + 1;
                    }
                } else if (nums[mid] < nums[start]) {
                    if (nums[mid] < nums[mid - 1]) {
                        break;
                    } else {
                        end = mid - 1;
                    }
                } else {
                    mid = nums[end] > nums[start] ? start : end;
                    break;
                }
            }
            return nums[mid];
        }
    }
    **/
    /** More consice way Nov. 9 2021
     *
     */
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = left + (right - left) / 2;
            
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else if (nums[mid] == nums[right]) {
                return nums[mid];
            } else {
                right = mid;
            }
        }
        return nums[mid];
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
