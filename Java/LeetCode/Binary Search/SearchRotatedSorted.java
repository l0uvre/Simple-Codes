// Leetcode 33. Binary Search.
public class SearchRotatedSorted {

    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else {
                /** make conditions on the sorted part,
                 * corner case: left can be equal to mid.
                 * */
                if (nums[left] <= nums[mid]) {
                    if (nums[left] <= target && 
                            target < nums[mid]) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                } else {
                    if (nums[mid] < target && 
                            target <= nums[right]) {

                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
            }
        }

        return nums[left] == target ? left : -1;
    }


    public int search2(int[] nums, int target) {
        int res = -1;
        if (nums == null || nums.length == 0) {
            return res; 
        } else if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        } else {
            int left = 0, right = nums.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] == target) {
                    return mid;
                }
                    
                if (nums[mid] > nums[right]) {
                    if (nums[mid] < target) {
                        left = mid + 1;
                    } else {
                        /** make conditions on the sorted subarray.*/
                        if (nums[left] <  target) {
                            right = mid - 1;
                        } else if (nums[left] == target) {
                            return left;
                        } else {
                            left = mid + 1;
                        }
                    } 
                } else {
                    if (nums[mid] < target) {
                        if (nums[right] > target) {
                            left = mid + 1;
                        } else if (nums[right] < target) {
                            right = mid - 1;
                        } else {
                            return right;
                        }
                    } else {
                        right = mid - 1;
                    }                    
                }
            }
            return res;
        }
    }
    public static void main(String[] args) {
        SearchRotatedSorted srs = new SearchRotatedSorted();
        int[] nums = new int[]{4, 5, 6, 7, 0, 1, 2};
        int target = 0;
        System.out.println(srs.search(nums, target));
        target = 3;
        System.out.println(srs.search(nums, target));
        target = 6;
        System.out.println(srs.search(nums, target));
        target = 4;
        System.out.println(srs.search(nums, target));
        target = 2;
        System.out.println(srs.search(nums, target));
    }
}
