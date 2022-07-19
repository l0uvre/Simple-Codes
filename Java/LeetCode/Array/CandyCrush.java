/** LC 723 -- Matrix, Array, Simulation. **/
public class CandyCrush {
    /** just follow the rules:
     * 1, traverse the matrix, find three or more candies of 
     * same type vertically and horizontally  and mark them as negative;
     * 2, loop over the columns, move positive candies downwards, record 
     * the pointer for the last positive candy;
     * 3, fill the columns with zeros by decrementing the pointer. **/
    public int[][] candyCrush(int[][] board) {
        int m = board.length;
        int n = board[0].length;

        boolean isEnd = false;

        while(!isEnd) {
            isEnd = true;
            /** mark the three or more candies of the same type horizontally and vertically. **/
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int val = Math.abs(board[i][j]);
                    if (val == 0) {
                        /** there is no candies for zeros. **/
                        continue;
                    }
                    /** we need to crush candies horizontally. **/
                    if (j + 2 < n && Math.abs(board[i][j + 1]) == val &&
                            Math.abs(board[i][j + 2]) == val) {
                        isEnd = false;
                        for (int k = 0; k < 3; k++) {
                            board[i][j + k] = -val;
                        }
                            }
                     /** we need to crush candies vertically. **/
                    if (i + 2 < m && Math.abs(board[i + 1][j]) == val &&
                            Math.abs(board[i + 2][j]) == val) {
                        isEnd = false;
                        for (int k = 0; k < 3; k++) {
                            board[i + k][j] = -val;
                        }
                    }
                }
            }

            if (isEnd) {
                break;
            }

            /** loop over the columns and move candies as low as possible. **/
            for (int j = 0; j < n; j++) {
                /** the pointer pointed at the position a positive candy falls at;
                 * initially at the bottome. **/
                int ptr = m - 1; 
                for (int i = m - 1; i >= 0; i--) {
                    if (board[i][j] > 0) {
                        board[ptr][j] = board[i][j];
                        ptr -= 1;
                    }
                }
                /** crush the candies, mark negative ones as zero. **/
                for (int i = ptr; i >= 0; i--) {
                    board[i][j] = 0;
                }
            }
        }
        return board;
    }
}
