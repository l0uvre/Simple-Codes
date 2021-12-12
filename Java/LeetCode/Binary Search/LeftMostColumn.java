/** LC 1428 -- Binary Search. **/
public class LeftMostColumn {

/**
 * // This is the BinaryMatrix's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface BinaryMatrix {
 *     public int get(int row, int col) {}
 *     public List<Integer> dimensions {}
 * };
 */

    /** for each row, do a binary search to find the leftmost one
     * on that row and update the answer. **/
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        int m = binaryMatrix.dimensions().get(0);
        int n = binaryMatrix.dimensions().get(1);
        int leftMost = n;

        for (int i = 0; i < m; i++) {
            int left = 0;
            int right = n - 1;
            while (left < right) {
                int col = left + (right - left) / 2;
                if (binaryMatrix.get(i, col) == 0) {
                    left = col + 1;
                } else {
                    right = col;
                }
            }
            if (binaryMatrix.get(i, left) == 1) {
                leftMost = Math.min(left, leftMost);
            }
        }
        return leftMost == n ? -1 : leftMost;
    }
}
