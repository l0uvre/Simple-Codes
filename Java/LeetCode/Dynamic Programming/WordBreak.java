/** LC 139 -- Dynamic Programming. **/
import java.util.*;

class WordBreak {

    /** dp, bottom up approach. **/
    public boolean wordBreak(String s, List<String> wordDict) {
        /** dp[i] means if s[0:i] the prefix is valid;
         * then dp[j] = true if dp[i] & s[i:j] is in wordDict.
         * */
        boolean[] dp = new boolean[s.length() + 1];

        /** base case "" is valid. **/
        dp[0] = true;

        Set<String> words = new HashSet<>(wordDict);

        /** calculate dp[1] to dp[n + 1]. **/
        for (int j = 1; j <= s.length(); j++) {
            for (int i = 0; i < j; i++) {
                if (dp[i] && 
                        words.contains(s.substring(i, j))) {
                    dp[j] = true;
                    /** find a vaild case for prefix s[:j], go
                     * to check s[: j+1]. **/
                    break;
                }
            }
        }
        /** whether s[0:n+1] is valid. **/
        return dp[s.length()];
    }

    public static void main(String[] args) {
        WordBreak sol = new WordBreak();
        System.out.println(sol.wordBreak("leetcode", Arrays.asList("leet", "code")));
    }
}
