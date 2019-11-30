import java.util.*;

public class LetterCasePermutation {
    public List<String> letterCasePermutation(String S) {
        List<String> res = new LinkedList<>();
        if (S == null || S.length() == 0) {
            return res;
        } else {
            dfs(res, "", S, 0); 
            return res;
        }
    }
    private void dfs(List<String> res, String curr, String S, int i) {
        if (i > S.length()) {
            return; 
        } else if (i == S.length()) {
            res.add(curr);
        } else {
            char currChar = S.charAt(i);
            if (Character.isDigit(currChar)) {
                dfs(res, curr + currChar, S, i + 1);
            } else if (Character.isLetter(currChar)) {
                dfs(res, curr + Character.toLowerCase(currChar), S, i + 1);
                dfs(res, curr + Character.toUpperCase(currChar), S, i + 1);
            }
        }
    }

    public static void main(String[] args) {
        LetterCasePermutation lcp = new LetterCasePermutation();
        System.out.println(lcp.letterCasePermutation("a1b2"));
        System.out.println(lcp.letterCasePermutation("3z4"));
        System.out.println(lcp.letterCasePermutation("12345"));
        System.out.println(lcp.letterCasePermutation("ubc"));
    }
}
