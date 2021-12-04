/*** LC240 -- Matrix, Array ***/
public class Search2DMatrixII {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        /** Search from the right top ***/
        int row = 0, col = n - 1;

        /*** O(m + n) **/
        while (row < m && col >= 0) {
            if (target == matrix[row][col]) {
                return true;
            } else if (target < matrix[row][col]) {
                col--;
            } else {
                row++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
            {1,4,7,11,15},
                {2,5,8,12,19},
                {3,6,9,16,22},
                {10,13,14,17,24},
                {18,21,23,26,30} 
        };
        int target = 20;
        Search2DMatrixII sol = new Search2DMatrixII(); 
        System.out.println(sol.searchMatrix(matrix, target));

        target = 5;
        System.out.println(sol.searchMatrix(matrix, target));
    }
}
