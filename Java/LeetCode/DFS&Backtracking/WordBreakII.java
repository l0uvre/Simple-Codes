/*** LC 140 -- DFS + Memoization **/
import java.util.*;
public class WordBreakII {

    /** do a dfs, starting from a valid prefix of s, and continue
     * search s[len(prefix) : ], finally concatnate them; using 
     * memoization to reuse the results. **/
    public List<String> wordBreak(String s, List<String> wordDict) {
        return dfs(s, new HashSet<>(wordDict), new HashMap<>());
    }

    /** return a list of sentences that consist of separate words 
     * by adding space to string s. **/
    private List<String> dfs(String s, Set<String> wordDict, 
            Map<String, List<String>> memo) {
        if (memo.containsKey(s)) {
            return memo.get(s);
        }
        
        List<String> sentences = new ArrayList<>();
        if (wordDict.contains(s)) {
            sentences.add(s);
        }
        
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                List<String> suffixRes = dfs(s.substring(word.length()),
                            wordDict, memo);
                if (!suffixRes.isEmpty()) {
                    for (String suffix : suffixRes) {
                        sentences.add(word + " " + suffix);
                    }
                }
            }
        }
        memo.put(s, sentences);
        return sentences;
    }

    public static void main(String[] args) {
        WordBreakII sol = new WordBreakII(); 
        String s = "catsanddog";
        List<String> wordDict = Arrays.asList("cat","cats","and","sand","dog");
        System.out.println(sol.wordBreak(s, wordDict));

        s = "pineapplepenapple";
        wordDict = Arrays.asList("apple","pen","applepen","pine","pineapple");
        System.out.println(sol.wordBreak(s, wordDict));

        s = "catsandong";
        wordDict = Arrays.asList("cats","dog","sand","and","cat");
        System.out.println(sol.wordBreak(s, wordDict));
    }

}
