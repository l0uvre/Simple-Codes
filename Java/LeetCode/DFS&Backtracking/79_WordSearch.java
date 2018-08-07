public class Solution {
    int[] rows = {0, 0, 1, -1};
    int[] cols = {1, -1, 0, 0};
    boolean[][] visited;
    public boolean exist(char[][] board, String word) {
        int M = board.length;
        int N = board[0].length;
        visited = new boolean[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (DFS(i, j, board, word, 1)) {
                        return true;
                    }
                }
            }         
        }
        return false;
        
    }
    
    private boolean DFS(int row, int col, char[][] board, String word, int index) {
        if (index >= word.length()) {
            return true;
        }
        visited[row][col] = true;
        for (int i = 0; i < 4; i++) {
            int newRow = rows[i] + row;
            int newCol = cols[i] + col;
            if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[newRow].length) {
                if (!visited[newRow][newCol] && board[newRow][newCol] == word.charAt(index)) {
                    if (DFS(newRow, newCol, board, word, index + 1)) {
                        return true;
                    } 
                } 
            }
        }
        visited[row][col] = false;
        
        return false;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        char[][] board = {{'a', 'b'}, {'c', 'd'}};
        System.out.println(sol.exist(board, "acdb"));
        char[][] board2 = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}}; 
        System.out.println(sol.exist(board2, "ABCCED"));
        System.out.println(sol.exist(board2, "SEE"));
        System.out.println(sol.exist(board2, "ABCB"));
    }
    
    
}
