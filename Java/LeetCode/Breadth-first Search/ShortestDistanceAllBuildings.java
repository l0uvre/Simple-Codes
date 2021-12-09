/** LC 317 -- BFS, graph **/
import java.util.*;
public class ShortestDistanceAllBuildings {
    private class Point {
        int x;
        int y;
        int distance;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
            distance = 0;
        }

        Point(int x, int y, int d) {
            this.x = x;
            this.y = y;
            distance = d;
        }
    }


    private static int[] DX = {1, -1, 0, 0};
    private static int[] DY = {0, 0, -1, 1};
    
    /** my naive approach: bfs from every '0' and 
     * calculate the shortest distance; O(lands*m*n);
     *
     * Better approach: bfs from every '1' and 
     * use count[i][j] respresents the number of
     * building it can reach, distance[i][j] as
     * the total distance from that point to all the
     * buildings, O(buildings * m*n).  **/

    public int shortestDistance(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int numOfBuilding = 0;
        int[][] count = new int[m][n];
        int[][] dis = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    numOfBuilding += 1;
                    bfs(i, j, grid, count, dis);
                }
            }
        }

        int shortest = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && 
                        count[i][j] == numOfBuilding) {
                    shortest = Math.min(shortest, 
                            dis[i][j]);
                } 
            }
        }
        return shortest == Integer.MAX_VALUE ? -1 : shortest;
    }

    private void bfs(int i, int j, int[][] grid,
            int[][] count, int[][] dis) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<Point> q = new LinkedList<>();
        /** add the house to the queue **/
        q.offer(new Point(i, j));

        while (!q.isEmpty()) {
            Point curr = q.poll();

            if (visited[curr.x][curr.y]) {
                continue;
            }
            visited[curr.x][curr.y] = true;
            dis[curr.x][curr.y] += curr.distance;
            count[curr.x][curr.y] += 1;

            for (int k = 0; k < DX.length; k++) {
                int x1 = curr.x + DX[k];
                int y1 = curr.y + DY[k];
                if (x1 >= 0 && x1 < m && y1 >= 0
                        && y1 < n && !visited[x1][y1]) {
                    if (grid[x1][y1] == 0) {
                        //dis[x1][y1] += (curr.distance + 1);
                        //count[x1][y1] += 1;
                        //visited[x1][y1] = true;
                        q.offer(new Point(x1, y1, 
                                    curr.distance + 1));
                    }
                }
            }
        }
    }


    /***
    public int shortestDistance(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int numOfBuilding = 0;
        for (int[] row : grid) {
            for (int p : row) {
                if (p == 1) {
                    numOfBuilding += 1;
                }
            }
        }
        int shortest = Integer.MAX_VALUE;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) {
                    int currDistance = bfs(i, j, grid,
                            numOfBuilding);
                    if (currDistance != -1) {
                        shortest = Math.min(shortest,
                                currDistance);
                    }
                }
            }
        }
        return shortest == Integer.MAX_VALUE ? -1 : shortest;
    }***/


    /** return the total distance from grid[i][j]
     * to all buildings; if it's not possible, return -1;
     * do a breadth first search. 
    private int bfs(int i, int j, int[][] grid, 
            int numOfBuilding) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int numOfBuildingVisited = 0;
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(i, j));

        int totalDistance = 0;

        while (!q.isEmpty()) {
            Point curr = q.poll();
            visited[curr.x][curr.y] = true;
            for (int k = 0; k < DX.length; k++) {
                int x1 = curr.x + DX[k];
                int y1 = curr.y + DY[k];
                if (x1 >= 0 && x1 < m && y1 >= 0
                        && y1 < n && !visited[x1][y1]) {
                    if (grid[x1][y1] == 1) {
                        totalDistance += curr.distance + 1;
                        numOfBuildingVisited += 1;
                        visited[x1][y1] = true;
                        if (numOfBuilding == numOfBuildingVisited) {
                            return totalDistance; 
                        }
                    } else if (grid[x1][y1] == 0) {
                        q.offer(new Point(x1, y1, 
                                    curr.distance + 1));
                    }
                }
            }
        }

        if (numOfBuilding == numOfBuildingVisited) {
            return totalDistance; 
        } else {
            return -1;
        }
    }**/

    public static void main(String[] args) {
        ShortestDistanceAllBuildings sol = 
            new ShortestDistanceAllBuildings(); 
        int[][] grid = new int[][]{
            {1,0,2,0,1},
                {0,0,0,0,0},
                {0,0,1,0,0}
        };
        System.out.println(sol.shortestDistance(grid));

        grid = new int[][]{
            {1, 0}
        };
        System.out.println(sol.shortestDistance(grid));

        grid = new int[][]{{1}};
        System.out.println(sol.shortestDistance(grid));

        grid = new int[][]{{0,2,0,0,0,0,0,2,0,0,0,2,0,2,0,2,2,2,2,2,2,0,0,0,2},{2,2,0,0,2,2,0,1,2,2,0,0,0,2,2,0,0,0,2,0,2,0,0,0,0},{0,2,0,0,0,2,2,1,0,0,0,0,0,0,2,0,1,0,0,0,0,0,2,0,2},{0,0,2,0,2,0,2,0,0,0,0,0,0,1,0,2,2,1,2,2,0,2,0,0,0},{2,0,0,0,2,2,0,0,0,1,0,2,0,2,0,0,0,0,0,1,0,0,2,2,2},{0,1,2,0,0,2,0,0,0,2,2,0,0,2,0,2,0,2,2,0,0,0,0,0,0},{0,0,2,0,0,2,0,0,0,0,2,2,0,2,1,0,0,2,0,2,0,0,0,2,2},{2,0,1,0,2,0,0,2,0,0,0,0,0,2,0,2,0,2,0,2,2,2,0,0,0},{2,0,0,1,2,0,0,0,0,0,1,0,0,2,0,0,0,2,2,0,0,2,0,0,0},{2,2,2,0,0,0,0,1,0,2,0,0,2,0,2,2,2,0,2,2,0,0,0,2,0},{0,2,0,0,2,0,2,0,0,0,0,2,2,2,0,0,0,1,0,0,0,0,0,2,2},{0,0,0,2,1,2,0,2,0,0,0,2,0,0,0,2,2,0,0,0,0,0,0,2,0},{2,2,2,2,2,2,0,0,0,0,2,0,2,0,2,2,2,0,0,2,0,0,2,0,1},{0,0,0,0,1,0,0,0,2,0,2,0,0,0,1,0,2,0,2,2,0,2,0,0,0},{2,0,2,0,2,0,0,0,0,0,2,2,0,0,2,0,0,2,2,0,0,2,2,0,2},{0,1,0,0,0,0,2,2,0,0,2,0,0,2,2,2,0,0,0,2,2,2,2,2,0},{0,2,2,1,0,0,0,0,0,0,0,0,0,0,2,0,0,0,1,0,0,0,0,0,0},{1,0,0,2,0,0,0,1,0,0,0,2,0,1,0,0,2,0,2,0,0,2,0,2,2},{0,0,0,0,2,0,0,0,0,0,2,2,1,0,2,2,0,0,0,0,2,2,0,0,0},{2,2,2,0,0,2,0,0,0,0,0,2,0,0,0,1,1,0,0,0,2,0,0,2,0},{2,2,2,0,0,1,0,2,0,0,0,0,1,2,2,0,0,0,2,0,0,0,0,1,0},{0,2,2,1,0,2,0,0,2,0,0,0,2,2,0,1,0,2,0,2,0,2,0,2,0},{1,2,0,2,0,0,0,2,0,2,2,2,0,0,2,0,0,0,0,0,0,2,0,0,0}};
        System.out.println(sol.shortestDistance(grid));
    }
}
