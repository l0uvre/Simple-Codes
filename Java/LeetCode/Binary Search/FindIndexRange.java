// Leetcode 34 Binary Search
public class FindIndexRange {

    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[] {-1, -1};
        if (nums == null || nums.length == 0) {
            return res;
        }
        int start = 0, end = nums.length - 1;
        int find = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                find = mid;
                break;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        
        if (find != -1) {
            for (int i = find; ; i--) {
                if (i < 0 || nums[i] != target) {
                    res[0] = i + 1;
                    break;
                }
            } 

            for (int i = find; ; i++) {
                if (i >= nums.length || nums[i] != target) {
                    res[1] = i - 1;
                    break;
                }
            } 
        }
        return res;
    }

    private static void print(int[] nums) {
        for (int e : nums) {
            System.out.print(e + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        FindIndexRange fir = new FindIndexRange();
        int[] nums = new int[]{2, 2};
        int target = 2;
        print(fir.searchRange(nums, target));

        nums = new int[]{5, 7, 7, 8, 8, 10};
        target = 8;
        print(fir.searchRange(nums, target));

        target = 5;
        print(fir.searchRange(nums, target));

        target = 10;
        print(fir.searchRange(nums, target));

    }

}
