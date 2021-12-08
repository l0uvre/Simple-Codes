/*** LC 162 --- Binary Search ***/
public class PeakElement {

    /** the two ends of nums are -inf;
     * do binary search, and compare nums[mid] to 
     * nums[mid + 1]:
     *   if nums[mid] < nums[mid + 1], our search range
     *   became [mid + 1, right], cause the right end is -inf
     *   there will be a peak that falls into -inf;
     *   else, our search range became [left, mid], cause the left end is -inf, there
     *   will be a local max on the left;
     *   **/
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
