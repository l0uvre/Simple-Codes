/** LC 562 --- DP, matrix. **/

public class LongestLineConsecutiveOneMatrix {

    /** use dynamic programming;
     * subproblems: 
     *  dp[i][j][4] holds the lengths of the longest lines with 
     *  ending point as mat[i][j]
     *  in four directions, horizontal, vertical, diagonal and
     *  anti-diagonal;
     *
     * relations:
     *  dp[i][j][2] = max(0 or 1, dp[i - 1][j][2])
     *  etc;
     *
     * original problems: 
     *  return the max value in the process of solving all the subprobs
     *. **/
    public int longestLine(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        int[][][] dp = new int[m][n][4];

        int longestLen = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    for (int k = 0; k < 4; k++) {
                        dp[i][j][k] = 1;
                        longestLen = Math.max(longestLen, dp[i][j][k]); 
                    }
                    /** 0 index considering the vertical direction. **/
                    if (i - 1 >= 0) {
                        dp[i][j][0] = Math.max(1, 1 + dp[i - 1][j][0]);
                        longestLen = Math.max(longestLen, dp[i][j][0]); 
                    } 
                    /** 1 holds the value for the horizontal direction. **/
                    if (j - 1 >= 0) {
                        dp[i][j][1] = Math.max(1, 1 + dp[i][j - 1][1]);
                        longestLen = Math.max(longestLen, dp[i][j][1]); 
                    }
                    /** 2 holds the value for the diagonal direction. **/
                    if (i - 1 >= 0 && j - 1 >= 0) {
                        dp[i][j][2] = Math.max(1, 1 + dp[i - 1][j - 1][2]);
                        longestLen = Math.max(longestLen, dp[i][j][2]); 
                    }
                    /** 3 holds the value for the anti-diagonal direction. **/
                    if (i - 1 >= 0 && j + 1 < n) {
                        dp[i][j][3] = Math.max(1, 1 + dp[i - 1][j + 1][3]);
                        longestLen = Math.max(longestLen, dp[i][j][3]); 
                    }
                }
            }
        }
        return longestLen;
    }

    public static void main(String[] args) {

        LongestLineConsecutiveOneMatrix sol = 
            new LongestLineConsecutiveOneMatrix(); 
        int[][] mat = new int[][]{
                {0,1,1,0},
                {0,1,1,0},
                {0,0,0,1}
        };
        System.out.println(sol.longestLine(mat));

        mat = new int[][]{
                {1,1,1,1},
                {0,1,1,0},
                {0,0,0,1}
        };
        System.out.println(sol.longestLine(mat));
    }
}
