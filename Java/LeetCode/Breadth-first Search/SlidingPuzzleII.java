import java.util.*;

public class SlidingPuzzleII {
    
    private static int[] dir = {1, -1, 3, -3};
    public List<int[][]> solve(int[][] board) {
        List<int[][]> res = new LinkedList<>(); 
        if (board == null || board.length == 0) {
            return res;
        }

        String target = "123456780";
        String start = serialize(board);

        Queue<List<String>> q = new LinkedList<>();
        Set<String> set = new HashSet<>();
        
        List<String> sList = new ArrayList<>();
        sList.add(start);
        q.offer(sList);
        while (!q.isEmpty()) {
           List<String> fringe = q.poll();
           String curr = fringe.get(fringe.size() - 1);
           if (curr.equals(target)) {
               for (String str : fringe) {
                   res.add(deSerialize(str));
               }
               return res;
           }
           if (!set.contains(curr)) {
               set.add(curr);
               int zeroIndex = curr.indexOf("0");
               for (int i = 0; i < dir.length; i++) {
                   int newZero = zeroIndex + dir[i];
                   if (newZero >= 0 && newZero < curr.length()) {
                       if ((zeroIndex == 2 && newZero == 3) || (zeroIndex == 3 && newZero == 2) || (zeroIndex == 5 && newZero == 6) || (zeroIndex == 6 
                       && newZero == 5)) {
                           continue;
                       }
                       String next = swap(curr, zeroIndex, newZero);
                       List<String> nextFringe = new ArrayList<>(fringe);
                       nextFringe.add(next);
                       q.add(nextFringe);
                   }
               }
           }
        }

        return res;
    }
    
    private String serialize(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : board) {
            for (int ele : row) {
                sb.append(ele);
            } 
        }
        return sb.toString();
    }

    private int[][] deSerialize(String str) {
        int[][] board = new int[3][3];
        for (int i = 0; i < str.length(); i++) {
            int col = i % 3;
            int row = i / 3;
            board[row][col] = str.charAt(i) - '0';
        }
        return board;
    }
    private String swap(String str, int i, int j) {
        char[] sb = str.toCharArray();
        char tmp = str.charAt(i);
        sb[i] = sb[j];
        sb[j] = tmp;
        return new String(sb);
    }

    private static void show(int[][] board) {
        for (int[] row : board) {
            for (int ele : row) {
                System.out.print(ele + " ");
            }

            System.out.println();
        }
        System.out.println();

    }

    public static void main(String[] args) {
        int[][] board = new int[][]{{1,2,3},{4,0,6},{7,5,8}};
        SlidingPuzzleII sol = new SlidingPuzzleII();
        List<int[][]> res = sol.solve(board);
        for (int[][] step : res) {
            show(step);
        }
        board = new int[][]{{4,0,2},{5,3,8},{6,1,7}};
        res = sol.solve(board);
        for (int[][] step : res) {
            show(step);
        }
        board = new int[][]{{0,1,3},{4,2,5},{7,8,6}};
        res = sol.solve(board);
        for (int[][] step : res) {
            show(step);
        }
        board = new int[][]{{1,2,3},{0,7,6},{5,4,8}};
        res = sol.solve(board);
        for (int[][] step : res) {
            show(step);
        }
    }
}
