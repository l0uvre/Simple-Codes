/*** LC 417 --- Graph, dfs ***/
import java.util.*;

public class PacificAtlanticWaterFlow {

    private static final int[] DX = {-1, 1, 0, 0};
    private static final int[] DY = {0, 0, 1, -1};
    private boolean[][] canFlowToP;
    private boolean[][] canFlowToA;

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> res = new ArrayList<>();
        int m = heights.length;
        int n = heights[0].length;
        boolean[][] visited = new boolean[m][n];
        /** used for memoization ***/
        canFlowToP = new boolean[m][n];
        canFlowToA = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfsToPacific(heights, i, j, visited) &&
                        dfsToAtlantic(heights, i, j, visited)) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }
        return res;
    }


    /** I used the backtracking + memoziation way to start 
     * from any point trying to flow to the ocean
     * But it can be solved directly by dfs from the ocean to 
     * the point
     */
    private boolean dfsToPacific(int[][] heights, int i, int j, 
            boolean[][] visited) {
        int m = visited.length;
        int n = visited[0].length;
        if (i == 0 || j == 0) {
            return true;
        } else {
            if (canFlowToP[i][j]) {
                return true;
            }
            visited[i][j] = true; 
            for (int k = 0; k < DX.length; k++) {
                int newI = i + DX[k];
                int newJ = j + DY[k];
                if (newI >= 0 && newI < m && newJ >= 0 &&
                        newJ < n) {
                    if (!visited[newI][newJ] && 
                            heights[newI][newJ] <= heights[i][j]
                            && dfsToPacific(heights, newI, newJ,
                                visited)) {
                        canFlowToP[i][j] = true;
                        /*** remember to backtrack ***/
                        visited[i][j] = false;
                        return true; 
                    }
                }
            }
            visited[i][j] = false;
            return false;
        }
    }

    private boolean dfsToAtlantic(int[][] heights, int i, int j, 
            boolean[][] visited) {
        int m = visited.length;
        int n = visited[0].length;
        if (i == m - 1||
                j == n - 1) {
            return true;
        } else {
            if (canFlowToA[i][j]) {
                return true;
            }
            visited[i][j] = true; 
            for (int k = 0; k < DX.length; k++) {
                int newI = i + DX[k];
                int newJ = j + DY[k];
                if (newI >= 0 && newI < m && newJ >= 0 &&
                        newJ < n) {
                    if (!visited[newI][newJ] && 
                            heights[newI][newJ] <= heights[i][j]
                            && dfsToAtlantic(heights, newI, newJ,
                                visited)) {
                        canFlowToA[i][j] = true;
                        /*** remember to backtrack ***/
                        visited[i][j] = false;
                        return true; 
                    }
                }
            }
            visited[i][j] = false;
            return false;
        }
    }

    public static void main(String[] args) {
        PacificAtlanticWaterFlow sol = new PacificAtlanticWaterFlow(); 
        int[][] heights = new int[][] {
            {1,2,2,3,5},
                {3,2,3,4,4},
                {2,4,5,3,1},
                {6,7,1,4,5},
                {5,1,1,2,4}
        };
        System.out.println(sol.pacificAtlantic(heights));

        heights = new int[][] {
            {2,1},
                {1,2}
        };
        System.out.println(sol.pacificAtlantic(heights));
    }

}
