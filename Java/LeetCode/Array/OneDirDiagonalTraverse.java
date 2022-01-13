/** Like LC 498 but it's only one directional;
 * reference : https://leetcode.com/discuss/interview-question/391708/Google-or-Phone-Screen-or-Print-Matrix-Diagonally. **/
import java.util.*;
public class OneDirDiagonalTraverse {

    /** Considering two steps:
     * print the top left part;
     * print the bottom right part. **/
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[] res = new int[m * n];

        int index = 0;

        /** print the top left part. **/
        for (int i = 0; i < m; i++) {
            int row = i;
            int col = 0;
            while (row >= 0 && col < n) {
                res[index] = mat[row][col];
                row--;
                col++;
                index++;
            }
        }

        /** print the bot right part. **/
        for (int j = 1; j < n; j++) {
            int row = m - 1;
            int col = j;
            while (row >= 0 && col < n) {
                res[index] = mat[row][col];
                index++;
                row--;
                col++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        OneDirDiagonalTraverse sol = new OneDirDiagonalTraverse(); 
        int[][] mat = new int[][]{
            {1,2,3},{4,5,6},{7,8,9}
        };

        System.out.println(Arrays.deepToString(mat));
        System.out.println(Arrays.toString(sol.findDiagonalOrder(mat)));

        mat = new int[][]{
            {1,  2,  3,  4},
                { 6,  7,  8,  9},
                {11, 12, 13, 14},
                {16, 17, 18, 19}
        };
        System.out.println(Arrays.deepToString(mat));
        System.out.println(Arrays.toString(sol.findDiagonalOrder(mat)));

        mat = new int[][]{
            { 1, 2, 3, 4 },     { 5, 6, 7, 8 },
            { 9, 10, 11, 12 },  { 13, 14, 15, 16 },
            { 17, 18, 19, 20 },
        };
        System.out.println(Arrays.deepToString(mat));
        System.out.println(Arrays.toString(sol.findDiagonalOrder(mat)));
    }
}
