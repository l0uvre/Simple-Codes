/** LC 1216 -- DP. **/
public class ValidPalindromeIII {

    /** dp, bottom up approach; substring problem. **/
    public boolean isValidPalindrome(String s, int k) {
        int n = s.length();
        /** dp[i][j] represents the number of characters to be deleted
         * to make the substring s[i: j+1] a valid palindrome;
         * the real problem is whether dp[0][n - 1] <= k. **/
        int[][] dp = new int[n][n];

        /** calculate the dp table from substring of length 2
         * to length n.  substring of length 1 don't need deletions. **/
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                /** substring s[i: i+len] **/
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    /** no need to delete chars. **/
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    /** delete 1 char. **/
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[0][n - 1] <= k;
    }

    public static void main(String[] args) {
        ValidPalindromeIII sol = new ValidPalindromeIII(); 
        String s = "abcdeca";
        int k = 2;
        System.out.println(sol.isValidPalindrome(s, k));

        s = "abbababa";
        k = 1;
        System.out.println(sol.isValidPalindrome(s, k));

        s = "s";
        k = 1;
        System.out.println(sol.isValidPalindrome(s, k));

        s = "bac";
        k = 1;
        System.out.println(sol.isValidPalindrome(s, k));

    }
}
