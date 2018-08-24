import java.util.*;

class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) {
            return true;
        } else if (wordDict == null || wordDict.size() == 0) {
            return false;
        }
        boolean[] dp = new boolean[s.length() + 1];
        Set<String> words = new HashSet<>(wordDict);
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j]) {
                    if (!dp[i]) {
                        dp[i] = words.contains(s.substring(j, i));
                    }
                }
            }
        }
        return dp[s.length()];
    }

    public static void main(String[] args) {
        WordBreak sol = new WordBreak();
        System.out.println(sol.wordBreak("leetcode", new ArrayList<>(Arrays.asList("leet", "code"))));
    }
}
