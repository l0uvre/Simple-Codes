/** LC 935 -- DP. **/
public class KnightDialer {
    /**
     * Original Problem: Number of length n we can dial following 
     * the rule;
     *
     * Sub Problem: Number of length i that ends with j (0 - 9)
     * and that we can dial; dp[i][j]
     *
     * Relations: dp[i][j] = sum(dp[i - 1][number_cells_to(j)])
     *
     * Original problem: sum(dp[n][j]) for j in 0...9. **/
    public int knightDialer(int n) {
        int mod = 1000000007;
        int[][] cellsTo = new int[][] {
            {4, 6}, // 0 -> [4, 6]
                {6, 8}, // 1 -> [6, 8]
                {7, 9},
                {4, 8},
                {0, 3, 9},
                {},
                {0, 1, 7},
                {2, 6},
                {1, 3},
                {2, 4},
        };
        int[][] dp = new int[n + 1][10];
        // base case: numbers of length 1
        for (int j = 0; j < 10; j++) {
            dp[1][j] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < 10; j++) {
                for (int prevCell : cellsTo[j]) {
                    dp[i][j] += dp[i - 1][prevCell];
                    dp[i][j] %= mod;
                }
            }
        }
        int res = 0;
        for (int j = 0; j <= 9; j++) {
            res += dp[n][j];
            res %= mod;
        }
        return res;
    }

    public static void main(String[] args) {
        KnightDialer sol = new KnightDialer();
        int n = 1;
        System.out.println(sol.knightDialer(n));

        n = 2;
        System.out.println(sol.knightDialer(n));

        n = 3131;
        System.out.println(sol.knightDialer(n));
    }
}
