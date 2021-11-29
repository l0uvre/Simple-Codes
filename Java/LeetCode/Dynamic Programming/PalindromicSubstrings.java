/*** LC 647 -- DP ****/
public class PalindromicSubstrings {
    public int countSubstrings(String s) {
        int n = s.length();
        int res = 0;
        /*** dp[i][j] means whether s[i: j + 1] is palindromic ***/
        boolean[][] dp = new boolean[n][n];

        /** single char string is palindromic **/
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
            res += 1;
        }

        /** two chars string***/
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                res += 1;
            }
        }

        int k = 2;
        /** length of the substring is k + 1, the rightmost
         * substring starts with (n - 1) - k = n - k - 1
         * */
        while ((n - k) > 0) {
            //int lenOfSubstring = k + 1;
            for (int i = 0; i < (n - k); i++) {
                if (s.charAt(i) == s.charAt(i + k)) {
                    if (dp[i + 1][i + k - 1]) {
                        dp[i][i + k] = true;
                        res += 1;
                    }
                }
            }
            k += 1;
        }
        return res;
        
    }

    public static void main(String[] args) {
        PalindromicSubstrings sol = new PalindromicSubstrings(); 
        System.out.println(sol.countSubstrings("abc"));
        System.out.println(sol.countSubstrings("aaa"));
        System.out.println(sol.countSubstrings("a"));
        System.out.println(sol.countSubstrings("ab"));
        System.out.println(sol.countSubstrings("aa"));

    }
}
