import java.util.*;

class SlidingPuzzle {
    
    private static int[] dir = {1, -1, 3, -3};
    
    public int slidingPuzzle(int[][] board) {
        String start = serialize(board);
        //System.out.println(start);
        String target = "123450";
        int res = 0;
        Set<String> visit = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        
        queue.offer(start);
        visit.add(start);
        
        while (!queue.isEmpty()) {
            for (int i = queue.size(); i > 0; i--) {
                String curr = queue.poll();
                if (curr.equals(target)) {
                    return res;
                } 
                int zeroPos = curr.indexOf('0');
                for (int j = 0; j < dir.length; j++) {
                    int nextPos = zeroPos + dir[j];
                    if (nextPos < 0 || nextPos > 5 || (zeroPos == 3 && nextPos == 2)
                       || (zeroPos == 2 && nextPos == 3)) {
                        continue;
                    } else {
                        String next = swap(curr, zeroPos, nextPos);
                        if (!visit.contains(next)) {
                            queue.offer(next);
                            visit.add(next);
                        }
                    }
                    
                }
            }
            res++;
        }
        
        return -1;
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
    
    private String swap(String str, int i, int j) {
        StringBuilder tmp = new StringBuilder(str);
        char iChar = str.charAt(i);
        tmp.setCharAt(i, str.charAt(j));
        tmp.setCharAt(j, iChar);
        return tmp.toString();
    }
    
    public static void main(String[] args) {
        SlidingPuzzle sol = new SlidingPuzzle();
        int[][] board = {{4, 1, 2}, {5, 0, 3}};
        System.out.println(sol.slidingPuzzle(board));
    }
}
