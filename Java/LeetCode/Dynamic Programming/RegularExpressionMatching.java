class RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        if (p == null || p.length() == 0) {
            return s.length() == 0;
        }
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        
        dp[s.length()][p.length()] = true;
        
        for (int i = p.length() - 1; i > 0; i--) {
            if (p.charAt(i) == '*' && dp[s.length()][i + 1]) {
                dp[s.length()][i - 1] = true; //corner case
            }
        }
        
        // subproblems; s[i: ] p[j: ] matching
        
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = p.length() - 1; j >= 0; j--) {
                char sChar = s.charAt(i);
                char pChar = p.charAt(j);
                if (sChar == pChar || pChar == '.') {
                    dp[i][j] = dp[i + 1][j + 1];
                } else if (pChar == '*') {
                    if (j > 0) {
                        if (p.charAt(j - 1) == sChar || p.charAt(j - 1) == '.') {
                            dp[i][j] = dp[i + 1][j] || dp[i + 1][j + 1] || dp[i][j + 1];
                        } else {
                            dp[i][j] = dp[i][j + 1];
                        }
                    }
                }
                
                if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                    dp[i][j] |= dp[i][j + 1];
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        String s = "bbbba";
        String p = ".*a*a"; 
        RegularExpressionMatching  sol = new RegularExpressionMatching();
        System.out.println(sol.isMatch(s,p)); 
        s = "a";
        p = ".*ab*";
        System.out.println(sol.isMatch(s,p)); 
    
    }
}
