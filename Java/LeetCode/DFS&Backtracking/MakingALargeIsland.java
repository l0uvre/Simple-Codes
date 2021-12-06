/***LC 827, Graph, DFS ***/
import java.util.*;
public class MakingALargeIsland {
    private static final int[] DX = {0, 0, -1, 1};
    private static final int[] DY = {1, -1, 0, 0};

    /*** DFS 1 grids, change grids value as indices
     * save the size of island into a hashmap map <index, size>
     * O(n*n)
     * **/
    public int largestIsland(int[][] grid) {
        Map<Integer, Integer> sizeMap = new HashMap<>();
        int n = grid.length;
        int color = 2;
        int maxSize = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int size = dfs(grid, i, j, color);
                    sizeMap.put(color, size);
                    maxSize = Math.max(maxSize, size);
                    color += 1;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    int size = connect(grid, i, j, new HashSet<>(),
                            sizeMap);
                    maxSize = Math.max(maxSize, size);
                }
            }
        }
        return maxSize;
    }

    /** paint the 1 grids using dfs, return the size of the island ***/
    private int dfs(int[][] grid, int i, int j, int color) {
        int size = 1;
        grid[i][j] = color;
        for (int k = 0; k < DX.length; k++) {
            int newI = i + DX[k];
            int newJ = j + DY[k];
            if (isInRange(newI, newJ, grid.length) &&
                    grid[newI][newJ] == 1) {
                size += dfs(grid, newI, newJ, color);
            }
        }
        return size;

    }

    private int connect(int[][] grid, int i, int j, Set<Integer> connected,
            Map<Integer, Integer> mapSize) {
        int size = 1;
        for (int k = 0; k < DX.length; k++) {
            int newI = i + DX[k];
            int newJ = j + DY[k];
            if (isInRange(newI, newJ, grid.length)) {
                int color = grid[newI][newJ];
                if (mapSize.containsKey(color) &&
                     !connected.contains(color)) {
                    size += mapSize.get(color);
                    /** avoid connect the same island **/
                    connected.add(color);
                }
            }
        }
        return size;
    }

    private boolean isInRange(int i, int j, int n) {
        return (i >= 0) && (i < n) &&
            (j >= 0) && (j < n);
    }

    public static void main(String[] args) {
        MakingALargeIsland sol = new MakingALargeIsland(); 
        int[][] grid = new int[][]{
            {1,0},{0,1}
        };
        System.out.println(sol.largestIsland(grid));

        grid = new int[][]{
            {1,1},
                {1,0}
        };
        System.out.println(sol.largestIsland(grid));

        grid = new int[][]{
            {1,1},
            {1,1}
        };
        System.out.println(sol.largestIsland(grid));
    }
}
