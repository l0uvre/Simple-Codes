/** LC 498 -- Matrix ***/
import java.util.*;

/*** LC 498 --- Matrix ***/
public class DiagonalTraverse {

    /** the diagonal index is from 0 to m + n - 2,
     * iterate the diagonal indices, find all the (row, col) valid
     * O((m + n)^2).**/
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        int[] res = new int[m * n];
        int index = 0;
        for (int k = 0; k <= m + n - 2; k++) {
            for (int i = 0; i <= k; i++) {
                int x = i;
                int y = k - x;
                /** it starts from col 0 **/
                if (k % 2 == 0) {
                    int tmp = x;
                    x = y;
                    y = tmp;
                }

                if (x < m && y < n) {
                    res[index] = mat[x][y];
                    index += 1;
                }
            }
        }
        return res;
    }

    /** on a diagonal line, the value of rowIndex + colIndex 
     * is consistent, build a list and reverse it accordingly **/
    public int[] findDiagonalOrderExtraSpace(int[][] mat) {
        int m = mat.length;        
        int n = mat[0].length;

        Map<Integer, List<Integer>> diagonal = new TreeMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                diagonal.putIfAbsent(i + j, new LinkedList<>());
                diagonal.get(i + j).add(mat[i][j]);
            }
        }

        int[] res = new int[m * n];
        int index = 0;
        for (int i : diagonal.keySet()) {
            List<Integer> line = diagonal.get(i);
            if (i % 2 == 0) {
                Collections.reverse(line);
            }
            for (int j : line) {
                res[index] = j;
                index += 1;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        DiagonalTraverse sol = new DiagonalTraverse();  
        int[][] mat = new int[][] {
            {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        System.out.println(Arrays.toString(sol.findDiagonalOrder(mat)));
    }
}
