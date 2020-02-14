//LC 36 hashtable
import java.util.*;

public class ValidSudoku {

    public boolean isValidSudoku(char[][] board) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char ch = board[i][j];
                if (ch != '.') {
                    if (!set.add(ch + "r" + i) || !set.add(ch + "c" + j)
                            ||!set.add(ch + "b" + i / 3 + j / 3)) {
                        return false;         
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ValidSudoku vs = new ValidSudoku();
        char[][] board = new char[][]{
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };
        System.out.println(vs.isValidSudoku(board));
    }

}
