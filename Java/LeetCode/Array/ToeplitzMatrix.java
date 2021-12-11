/** LC 766 -- Matrix. **/
public class ToeplitzMatrix {

    /** check whether each value is equal to the top left
     * neighbor. **/
    public boolean isToeplitzMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int topI = i - 1;
                int leftJ = j - 1;
                if (topI >= 0 && leftJ >= 0 &&
                    matrix[i][j] != matrix[topI][leftJ]) {
                    return false;
                }
            }
        }
        return true;
    }
}
