/****LC 79 --- DFS/Backtracking ***/
public class WordSearch {
    private final static int[] DX = new int[] {-1, 1, 0, 0};
    private final static int[] DY = new int[] {0, 0, 1, -1};
    private boolean[][] visited;

    public boolean exist(char[][] board, String word) {
        visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (dfs(board, i, j, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isInBound (char[][] board, int i, int j) {
        return (i >= 0 && i < board.length) &&
            (j >= 0 && j < board[i].length);
    }


    private boolean dfs(char[][] board, int row, int col, 
             String target, int index) {
        if (index == target.length()) {
            return true;
        }
        char ch = board[row][col];
        if (ch != target.charAt(index)) {
            return false;
        } else {
            if (index == target.length() - 1) {
                return true;
            }
            visited[row][col] = true;
            for (int i = 0; i < DX.length; i++) {
                int newRow = DX[i] + row;
                int newCol = DY[i] + col;
                if (isInBound(board, newRow, newCol) && !visited[newRow][newCol]) {
                    if (dfs(board, newRow, newCol, target, index + 1)) {
                        return true;
                    }
                }
            }
            visited[row][col] = false;
            return false;
        }
    }


    public static void main(String[] args) {
        char[][] board = new char[][]{

            {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String word = "ABCCED";
        WordSearch sol = new WordSearch();
        System.out.println(sol.exist(board, word));
        word = "ABCS";
        System.out.println(sol.exist(board, word));

    }

}
