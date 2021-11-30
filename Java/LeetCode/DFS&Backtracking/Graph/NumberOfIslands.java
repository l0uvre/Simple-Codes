class NumberOfIslands {
    private static int[] row = {0, 0, 1, -1};
    private static int[] col = {1, -1, 0, 0};
    private boolean[][] visited;
    
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int res = 0;
        int m = grid.length;
        int n = grid[0].length;
        visited = new boolean[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    dfs(i, j, grid);
                    
                    res++;
                }
            }
        }
        return res;
    }
    
    private void dfs(int i, int j, char[][] grid) {
        visited[i][j] = true;
        for (int k = 0; k < 4; k++) {
            int newRow = i + row[k];
            int newCol = j + col[k];
            if (newRow >= 0 && newRow < grid.length && 
                newCol >= 0 && newCol < grid[newRow].length) {
                if (grid[newRow][newCol] == '1' && !visited[newRow][newCol]) {
                    dfs(newRow, newCol, grid);
                }
            } 
        }
        
    }

    public static void main(String[] args) {
        char[][] grids = {{'1','1','0','0','0'},{'1','1','0','0','0'},{'0','0','1','0','0'},{'0','0','0','1','1'}};
        NumberOfIslands sol = new NumberOfIslands();
        System.out.println(sol.numIslands(grids));
    }
}
