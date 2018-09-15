class GameofLife {
    
    private static int[] x = {-1, 0, 0, 1, -1, 1, -1, 1};
    private static int[] y = {1, 1, -1, -1,-1, 1, 0, 0};
    
    public void gameOfLife(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        int[][] res = new int[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int neighbors = calLiveNeighbor(i, j, board);
                if (board[i][j] == 0) {
                    if (neighbors == 3) {
                        res[i][j] = 1;
                    } 
                } else {
                    if (neighbors < 2 || neighbors > 3) {
                        res[i][j] = 0;
                    } else {
                        res[i][j] = 1;
                    }
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = res[i][j];
            }
        }
        //board = res;
    }
    
    private boolean isValid(int i, int j, int[][] board) {
        if (i >= 0 && i < board.length && j >= 0 && j < board[i].length) {
            return true;
        }
        return false;
    }
    
    private int calLiveNeighbor(int i, int j, int[][] board) {
        int res = 0;
        for (int k = 0; k < 8; k++) {
            int newX = x[k] + i;
            int newY = y[k] + j;
            if (isValid(newX, newY, board) && board[newX][newY] == 1) {
                res++;
            }     
        }
        return res;
    }
    private static void print(int[][] board) {
        for (int[] row : board) {
            for (int ele : row) {
                System.out.print(ele + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void main(String[] args) {
        GameofLife sol = new GameofLife();
        int[][] board = new int[][]{{0,1,0},{0,0,1},{1,1,1},{0,0,0}};
        print(board);
        sol.gameOfLife(board);
        print(board);

        
    }
}
