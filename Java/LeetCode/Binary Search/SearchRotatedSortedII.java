//Leetcode 81 Binary Search
public class SearchRotatedSortedII {

    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        } else {
            int start = 0, end = nums.length - 1;
            while (start <= end) {
                int mid = start + (end - start) / 2;
                //System.out.println(start + " " + end);
                if (nums[mid] == target) {
                    return true;
                } else {
                    if (nums[mid] > nums[start]) {
                        if (nums[mid] < target) {
                            start = mid + 1;
                        } else {
                            if (nums[start] > target) {
                                start = mid + 1;
                            } else {
                                end = mid - 1; 
                            }
                        } 
                    } else if (nums[mid] < nums[start]) {
                        if (nums[mid] < target) {
                            if (nums[start] > target) {
                                start = mid + 1;
                            } else {
                                end = mid - 1;
                            }
                        } else {
                            end = mid - 1;
                        }
                    } else {// important skip duplicates 
                        start += 1;
                    }
                }
            }
        return false;
        }
    }

    public static void main(String[] args) {
        SearchRotatedSortedII srsi = new SearchRotatedSortedII();
        int[] nums = new int[]{2, 5, 6, 0, 0, 1, 2};
        int target = 0;
        System.out.println(srsi.search(nums, target));
        target = 3;
        System.out.println(srsi.search(nums, target));
        target = 2;
        System.out.println(srsi.search(nums, target));
        target = 1;
        System.out.println(srsi.search(nums, target));
        target = 6;
        System.out.println(srsi.search(nums, target));
        nums = new int[]{1,3,1,1,1};
        target = 3;
        System.out.println(srsi.search(nums, target));
    }

}
