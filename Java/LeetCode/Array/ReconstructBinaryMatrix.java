/*** LC 1253 --- Matrix, Array ***/
import java.util.*;
import java.util.stream.*;

public class ReconstructBinaryMatrix {

    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        int[][] matrix = new int[2][colsum.length];
        for (int i = 0; i < colsum.length; i++) {
            if (colsum[i] == 0) {
                matrix[0][i] = 0;
                matrix[1][i] = 0;
            } else if (colsum[i] == 2) {
                if (upper <= 0 || lower <= 0) {
                    return new ArrayList<>();
                } else {
                    matrix[0][i] = 1;
                    matrix[1][i] = 1;
                    upper -= 1;
                    lower -= 1;
                } 
            } else {
                /*** allocate the 1 to the larger one of upper and lower**/
                if (upper <= 0 && lower <= 0) {
                    return new ArrayList<>();
                } else if (upper >= lower) {
                    matrix[0][i] = 1;
                    upper -= 1;
                    matrix[1][i] = 0;
                } else {
                    matrix[0][i] = 0;
                    matrix[1][i] = 1;
                    lower -= 1;
                }
            }
        }

        if (upper != 0 || lower != 0) {
            return new ArrayList<>();
        } else {
            List<List<Integer>> res = new ArrayList<>();
            res.add(Arrays.stream(matrix[0])
                          .boxed()
                          .collect(Collectors.toList()));
            res.add(Arrays.stream(matrix[1])
                          .boxed()
                          .collect(Collectors.toList()));
            return res;
                                    
        }
    }

    public static void main(String[] args) {
        ReconstructBinaryMatrix sol = new ReconstructBinaryMatrix(); 
        int[] colsum = new int[]{2,1,2,0,1,0,1,2,0,1};
        int upper = 5;
        int lower = 5;
        System.out.println(sol.reconstructMatrix(upper, lower, colsum));
    }
}
