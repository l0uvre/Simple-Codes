class WildcardMatching{
    public boolean isMatch(String s, String p) {
         // Thank Erik Damaine's awesome lecture!
        // subproblem : s[0: i], p[0: j] prefixes
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        
        for (int i = 0; i < n; i++) {
            if (p.charAt(i) == '*') {
                dp[0][i + 1] = dp[0][i];
            }
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
                    dp[i + 1][j + 1] = dp[i][j];
                } else if (p.charAt(j) == '*') {
                    dp[i + 1][j + 1] = dp[i][j + 1] || dp[i][j] || dp[i + 1][j];
                }
            }
        }
        
        return dp[m][n];
    }

    public static void main(String[] args) {
        WildcardMatching sol = new WildcardMatching();
        String s = "aa";
        String p = "a";
        System.out.println(sol.isMatch(s, p));
        s = "cb";
        p = "?a";
        System.out.println(sol.isMatch(s, p));
        s = "adceb";
        p = "*a*b";
        System.out.println(sol.isMatch(s, p));
        s = "acdcb";
        p = "a*c?b";
        System.out.println(sol.isMatch(s, p));
        s = "acdcb";
        p = "a*c*b";
        System.out.println(sol.isMatch(s, p));


    }
}
