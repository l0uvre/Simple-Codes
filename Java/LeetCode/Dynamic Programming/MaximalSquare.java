class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = 1;
                    res = 1;
                }
            }
        } // initialize the memory table;
        
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (dp[i][j] == 1) {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1])
                                    , dp[i - 1][j - 1]) + 1; // solve the original problem using previous solutions
                    res = Math.max(dp[i][j], res);
                }
            } 
        }
        return res * res;
    }

    public static void main(String[] args) {
        MaximalSquare sol = new MaximalSquare();
        char[][] board = {
            {'1','0','1','0','0'},
            {'1','0','1','1','1'},
            {'1','1','1','1','1'},
            {'1','0','0','1','0'}
        };
        System.out.println(sol.maximalSquare(board));
    }
}
