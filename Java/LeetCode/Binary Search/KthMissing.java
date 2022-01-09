/** LC 1539 -- Binary Search. **/
public class KthMissing {

    /** Linear approach : O(N + k);
     * we just need to check the integer from 1 to len(arr) + k. **/
    public int findKthPositive(int[] arr, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i : arr) {
            set.add(i);
        }

        int count = 0;
        for (int i = 1; i <= arr.length + k; i++) {
            if (!set.contains(i)) {
                count += 1;
            }
            if (k == count) {
                return i;
            }
        }
        return -1;
    }

    /** https://leetcode.com/problems/kth-missing-positive-number/discuss/1004535/Python-Two-solutions-O(n)-and-O(log-n)-explained **/
    public int findKthPositiveBS(int[] arr, int k) {
        int left = 0;
        int right = arr.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            /** for arr[i], there is arr[i] - (i + 1) missing numbers
             * so far. **/
            if (arr[mid] - mid - 1 < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        /** look up the reference. **/
        return left + k;
    }
}
