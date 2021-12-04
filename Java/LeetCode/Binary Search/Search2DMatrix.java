/** LC 74 --- Matrix, Array, Binary Search ***/
public class Search2DMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        int left = 0;
        int right = m * n - 1;

        /** treat it as an array,
         * convert the indice and do binary search **/
        while (left < right) {
            int mid = left + (right - left) / 2;
            int row = mid / n;
            int col = mid % n;
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
                
        }
        return matrix[left/n][left%n] == target;
    }

    public static void main(String[] args) {
        Search2DMatrix sol = new Search2DMatrix(); 
        int[][] matrix = new int[][]{
            {1,3,5,7},
                {10,11,16,20},
                {23,30,34,60}
        };
        int target = 3;
        System.out.println(sol.searchMatrix(matrix, target));

        matrix = new int[][]{
            {1,3,5,7},
                {10,11,16,20},
                {23,30,34,60}
        };
        target = 13;
        System.out.println(sol.searchMatrix(matrix, target));

    }
}
