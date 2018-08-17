class SurroundedRegions {
    private static int[] rows = {0, 0, 1, -1};
    private static int[] cols = {1, -1, 0, 0};
    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        } else if (board.length < 3 && board[0].length < 3) {
            return;
        }
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                dfs(board, i, 0, 'S', 'O');
            }
            if (board[i][n - 1] == 'O') {
                dfs(board, i, n - 1, 'S', 'O');
            }
        }
        
        for (int j = 1; j < n - 1; j++) {
            if (board[0][j] == 'O') {
                dfs(board, 0, j, 'S', 'O');
            }
            if (board[m - 1][j] == 'O') {
                dfs(board, m - 1, j, 'S', 'O');
            }
        }
        for (int i = 0; i < m;  i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                
            }
        }
        for (int i = 0; i < m;  i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'S') {
                    board[i][j] = 'O';
                }
            }
        }
    }
    
    private void dfs(char[][] board, int row, int col, char ch, char oldChar) {
        board[row][col] = ch;
        for (int i = 0; i < 4; i++) {
            int newRow = row + rows[i];
            int newCol = col + cols[i];
            if (newRow >= 0 && newRow < board.length 
                && newCol >= 0 && newCol < board[newRow].length) {
                if (board[newRow][newCol] == oldChar) {
                   dfs(board, newRow, newCol, ch, oldChar); 
                } 
            }
        }
    }

    public void printBoard(char[][] board) {
        for (char[] row : board) {
            for (char ele : row) {
                System.out.print(ele + " ");
            }
            System.out.println();
        }
        System.out.println();
    
    }
    public static void main(String[] args) {
        char[][] board = {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
        SurroundedRegions sol = new SurroundedRegions();
        sol.printBoard(board);
        sol.solve(board);
        sol.printBoard(board);

    }
}
