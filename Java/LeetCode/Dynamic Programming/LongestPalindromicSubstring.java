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

        /** base case. length 1 . k == 0**/
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        /** base case. length 2. k == 1**/
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                if (minLen < 2) {
                    minLen = 2;
                    left = i;
                }
            }
        }

        int k = 2;
        /** build from bottom to top, while the length of 
         * substring is equal to smaller than n**/

        while (k + 1 <= n) {
            for (int i = 0; i < n - k; i++) {
                if (s.charAt(i) == s.charAt(i + k)) {
                    if (dp[i + 1][i + k - 1]) {
                        dp[i][i + k] = true;
                        int len = k + 1;
                        /** We found a longer palindromic substring ***/
                        if (minLen < len) {
                            minLen = len;
                            left = i;
                        }
                    }
                }
            }
            k++;
        }
        return s.substring(left, left + minLen);
    }
    
    public static void main(String[] args) {
        LongestPalindromicSubstring sol = new LongestPalindromicSubstring();
        System.out.println(sol.longestPalindrome("bbbbbdccccd"));
        System.out.println(sol.longestPalindrome("aam"));
        System.out.println(sol.longestPalindrome("bboc"));

    }
}
