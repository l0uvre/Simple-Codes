// Leetcode 33. Binary Search.
public class SearchRotatedSorted {
    
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0, end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            //System.out.println(start + " " + end);
            if (nums[mid] == target) {
                return mid;
            } else {
                if (nums[mid] < nums[start]) { 
                    if (nums[mid] > target) {
                        end = mid - 1;
                    } else {
                        if (nums[start] == target) {
                            return start;
                        } else if (nums[start] < target) {
                            end = mid - 1; 
                        } else {
                            start = mid + 1;
                        }
                    }
                } else {
                    if (nums[mid] > target) {
                        if (nums[start] == target) {
                            return start;
                        } else if (nums[start] > target) {
                            start = mid + 1; 
                        } else {
                            end = mid - 1;
                        }
                    } else {
                        start = mid + 1;
                    }
                }
            }
        } 
        return -1; 
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
