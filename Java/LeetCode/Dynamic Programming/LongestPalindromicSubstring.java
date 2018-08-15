class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        if (s == null) {
            return null;
        } else if (s.length() < 2) {
            return s;
        }
        int minLen = 1; int left = 0;
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        /** base case. length 1 **/
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        /** base case. length 2. **/
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
            }
        }
        /** Do the recursion **/
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j]) {
                    if (i - 1 >= 0 && j + 1 < n) {
                        dfs(dp, s, i - 1, j + 1);
                    }
                }
            }
        }
        /** find the maximum **/
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j]) {
                    if (j - i + 1 > minLen) {
                        minLen = j - i + 1;
                        left = i;
                    }
                }
            }
        }
        return s.substring(left, left + minLen);
    }
    
    private void dfs(boolean[][] dp, String s, int i, int j) {
        if (dp[i][j]) {
            return;
        }
        dp[i][j] = dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
        if (i - 1 >= 0 && j + 1 < s.length() && dp[i][j]) {
            dfs(dp, s, i - 1, j + 1);
        }
    }

    public static void main(String[] args) {
        LongestPalindromicSubstring sol = new LongestPalindromicSubstring();
        System.out.println(sol.longestPalindrome("bbbbbdccccd"));
        System.out.println(sol.longestPalindrome("aam"));
        System.out.println(sol.longestPalindrome("bboc"));

    }
}
