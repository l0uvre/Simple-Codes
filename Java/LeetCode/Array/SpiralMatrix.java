/*** LC 54 --- Array, Matrix ***/
import java.util.*;
public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        int rowBegin = 0, rowEnd = matrix.length - 1;
        int colBegin = 0, colEnd = matrix[0].length - 1;

        List<Integer> res = new ArrayList<>();
        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            for (int j = colBegin; j <= colEnd; j++) {
                res.add(matrix[rowBegin][j]);
            }
            rowBegin += 1;

            for (int i = rowBegin; i <= rowEnd; i++) {
                res.add(matrix[i][colEnd]);
            }
            colEnd -= 1;

            /*** Don't need to check colBegin and colEnd, 
             * if colEnd < colBegin, the for loop never starts.
             * ***/
            if (rowEnd >= rowBegin) {
                for (int j = colEnd; j >= colBegin; j--) {
                    res.add(matrix[rowEnd][j]);
                }
            }
            rowEnd -= 1;

            if (colEnd >= colBegin) {
                for (int i = rowEnd; i >= rowBegin; i--) {
                    res.add(matrix[i][colBegin]);
                }
            }
            colBegin += 1;
        }
        return res;
    }

    public static void main(String[] args) {
        SpiralMatrix sol = new SpiralMatrix();
        int[][] matrix = new int[][]{{1}};
        System.out.println(sol.spiralOrder(matrix));

        matrix = new int[][]{{1, 2, 3, 4}};
        System.out.println(sol.spiralOrder(matrix));

        matrix = new int[][]{{1}, {2}, {3}, {4}};
        System.out.println(sol.spiralOrder(matrix));

        matrix = new int[][]{{1,2,3,4}, {5,6,7,8}, {9,10,11,12}};
        System.out.println(sol.spiralOrder(matrix));

        matrix = new int[][]{{1,2,3}, {4,5,6}, {7,8,9}};
        System.out.println(sol.spiralOrder(matrix));
    }
}
