/** LC 286 -- BFS. **/
import java.util.*;
public class WallsGates {
    /** start a bfs from every gate, update the 
     * empty room with a closer distance to a gate. **/
    public void wallsAndGates(int[][] rooms) {
        int m = rooms.length;
        int n = rooms[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    bfs(i, j, rooms);
                }
            }
        }
    }

    private static final int[] DX = {-1, 1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};

    private void bfs(int i, int j, int[][] rooms) {
        int distance = 0;
        int m = rooms.length;
        int n = rooms[0].length;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{i, j});
        while (!q.isEmpty()) {
            distance += 1;
            for (int k = q.size(); k > 0; k--) {
                int[] pos = q.poll();
                for (int l = 0; l < DX.length; l++) {
                    int newI = DX[l] + pos[0];
                    int newJ = DY[l] + pos[1];
                    if (newI >= 0 && newI < m && newJ >=
                            0 && newJ < n) {
                        /** we don't need a visited set, just 
                         * add the point to the queue if the value of it
                         * is larger than the distance variable. **/
                        if (rooms[newI][newJ] > distance) {
                            q.offer(new int[]{newI, newJ});
                            rooms[newI][newJ] = distance;
                        }
                    }
                }
            }
        }
    }
}
