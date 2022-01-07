/** LC 1091 -- BFS. **/
import java.util.*;
public class ShortestPathBinaryMatrix {

    /** do a bfs on the matix;
     * we can use visited[n][n] or mark the grid[i][j] = 1;
     * for every layer on the bfs, increment the length of path;
     *
     * Attention: mark the nodes when adding them to the queue;
     * or check whether it's visited before expanding the node. **/
    private static final int[] DX = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static final int[] DY = {-1, 1, 0, -1, 1, -1, 0, 1};
    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid[0][0] != 0) {
            return -1;
        }
        int length = 0;
        int n = grid.length;
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        q.offer(new int[]{0, 0});
        while (!q.isEmpty()) {
            length += 1;
            for (int i = q.size(); i > 0; i--) { 
                int[] pos = q.poll();
                /** skip explored nodes. **/
                if (visited[pos[0]][pos[1]]) {
                    continue;
                }
                visited[pos[0]][pos[1]] = true;
                if (pos[0] == n - 1 
                        && pos[1] == n - 1) {
                    return length;
                } 

                for (int j = 0; j < DX.length; j++) {
                    int[] newPos = new int[2];
                    if (pos[0] + DX[j] >= 0 && pos[0] + DX[j] < n
                            && pos[1] + DY[j] >= 0 &&
                            pos[1] + DY[j] < n) {
                        newPos[0] = pos[0] + DX[j];
                        newPos[1] = pos[1] + DY[j];
                        if (grid[newPos[0]][newPos[1]] == 0
                                && visited[newPos[0]][newPos[1]] == false) {
                            q.offer(newPos);
                        }
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        ShortestPathBinaryMatrix sol = new ShortestPathBinaryMatrix();
        int[][] grid = new int[][]{
            {0,1},
                {1,0}
        };
        System.out.println(sol.shortestPathBinaryMatrix(grid));

        grid = new int[][]{
            {0,0,0},
                {1,1,0},
                {1,1,0}
        };
        System.out.println(sol.shortestPathBinaryMatrix(grid));

        grid = new int[][]{
            {1,0,0},
                {1,1,0},
                {1,1,0}
        };
        System.out.println(sol.shortestPathBinaryMatrix(grid));
    }
}
