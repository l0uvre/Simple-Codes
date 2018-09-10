class EditDistance {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        // subproblems: suffixes s1[i:], s2[j:]
        int[][] dp = new int[m + 1][n + 1];
        dp[m][n] = 0;
        
        for (int i = n - 1; i >= 0; i--) {
            dp[m][i] = dp[m][i + 1] + 1;
        }
        
        for (int i = m - 1; i >= 0; i--) {
            dp[i][n] = dp[i + 1][n] + 1;
        }  // initial distance. CAUTION!
        
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                //int tmp = Integer.MAX_VALUE;
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1]; // case1 : corresponding characters equal
                } else {
                    int tmp = dp[i + 1][j] + 1; // case2 : delete.
                    int tmp2 = Math.min(dp[i][j + 1] + 1, dp[i + 1][j + 1] + 1); // case3, 4: insert, replace;
                    dp[i][j] = Math.min(tmp, tmp2);
                }
            }
        }
        
        /**for (int i = n - 1; i >= 0; i--) {
            System.out.print(dp[m - 2][i] + " ");
        }**/
        
        return dp[0][0];
    }

    public static void main(String[] args) {
        EditDistance sol = new EditDistance();
        String s1 = "horse";
        String s2 = "ros";
        System.out.println(sol.minDistance(s1, s2));
        s1 = "intention";
        s2 = "execution";
        System.out.println(sol.minDistance(s1, s2));

    }
}
