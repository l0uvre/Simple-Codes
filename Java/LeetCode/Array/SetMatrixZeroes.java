/*** LC 73 --- Array, Matix ***/
import java.util.*;
public class SetMatrixZeroes {
    /***
     * Use the first element in every row and in every column 
     * as flag, if there is a zero in that row/column, set that bit to '0'
     * then we can traverse the rows from bottom to top
     * */
    public void setZeroes(int[][] matrix) {
        int col0 = 1; /** we need an extra flag for column 0 **/
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                col0 = 0;
            }
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        for (int i = matrix.length - 1; i >= 0; i--) {
            if (matrix[i][0] == 0) {
                Arrays.fill(matrix[i], 0);
                continue;
            }
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
            if (col0 == 0) {
                matrix[i][0] = 0;
            }
        } 
    }

    public static void main(String[] args) {
        SetMatrixZeroes sol = new SetMatrixZeroes(); 
        int[][] matrix = new int[][]{
            {1,1,1},
                {1,0,1},
                {1,1,1}
        };
        System.out.println(Arrays.deepToString(matrix));
        sol.setZeroes(matrix);
        System.out.println(Arrays.deepToString(matrix));
        System.out.println();

        matrix = new int[][] {
            {0,1,2,0},
                {3,4,5,2},
                {1,3,1,5}
        };
        System.out.println(Arrays.deepToString(matrix));
        sol.setZeroes(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }


}
