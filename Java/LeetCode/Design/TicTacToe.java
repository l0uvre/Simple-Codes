/** LC 348 -- Design, Array. **/
public class TicTacToe {

    /** every time one player calls move
     * player 1 add 1 to the rows[row];
     * player 2 add -1 to the rows[row];
     * if rows[row] == n / -n, then return the player;
     *
     * similarly for cols. **/
    int[] rows;
    int[] cols;
    int diagonal;
    int antiDiagonal;
    public TicTacToe(int n) {
        rows = new int[n];
        cols = new int[n];
        diagonal = 0;
        antiDiagonal = 0;
    }
    
    public int move(int row, int col, int player) {
        int add = (player == 1) ? 1 : -1;
        rows[row] += add;
        cols[col] += add;

        int n = rows.length;

        if (row == col) {
            diagonal += add;
        } 
        if (row + col == n - 1) {
            antiDiagonal += add;
        }
        if (Math.abs(rows[row]) == n || Math.abs(cols[col])
                    == n || Math.abs(diagonal) == n ||
                    Math.abs(antiDiagonal) == n) {
            return player;
                    }
        return 0;

    }
}
