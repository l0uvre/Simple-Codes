/** LC 852 -- Binary Search. **/
public class PeakIndexMountainArray {
    /** do a binary search, compare mid and mid + 1, as the mid prefers the left;
     * reduce the search space by half every time. **/
    public int peakIndexInMountainArray(int[] arr) {
        int left = 0;
        int right = arr.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < arr[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
