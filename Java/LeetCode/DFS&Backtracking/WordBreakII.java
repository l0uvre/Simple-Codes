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

    /**
     * My approach to solve the problem using the traditional backtracking;
     * Use the classic backtracking pattern like on arrays/strings, the 
     * sequential data;
     *
     * 1. Given a starting index, find a vaild prefix of String s that is in the wordDict.
     * 2. Add the valid word to the current sentence, and do the dfs on s[len(prefix) : ].
     * 3. Backtrack by restoring the current sentence, conitnue the dfs by going to step 1.
     *
     * **/
    public List<String> wordBreak2(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        dfs(s, 0, new StringBuilder(), new HashSet<>(wordDict), res);
        return res;
    }

    /** this approach is better for short string s and
     * large collections of dict. **/
    private void dfs(String s, int index, StringBuilder currSen,
            Set<String> dict, List<String> res) {
        if (index > s.length()) {
            return;
        } else if (index == s.length()) {
            res.add(currSen.toString());
            return;
        }
        for (int i = index; i < s.length(); i++) {
            String word = s.substring(index, i + 1);
            if (dict.contains(word)) {
                int originalLen = currSen.length();
                if (currSen.length() == 0) {
                    currSen.append(word);
                } else {
                    currSen.append(" ").append(word);
                }
                dfs(s, i + 1, currSen,
                        dict, res);
                currSen.setLength(originalLen);
            }
        }
    }


    public static void main(String[] args) {
        WordBreakII sol = new WordBreakII(); 
        String s = "catsanddog";
        List<String> wordDict = Arrays.asList("cat","cats","and","sand","dog");
        System.out.println(sol.wordBreak2(s, wordDict));

        s = "pineapplepenapple";
        wordDict = Arrays.asList("apple","pen","applepen","pine","pineapple");
        System.out.println(sol.wordBreak2(s, wordDict));

        s = "catsandong";
        wordDict = Arrays.asList("cats","dog","sand","and","cat");
        System.out.println(sol.wordBreak2(s, wordDict));
    }

}
