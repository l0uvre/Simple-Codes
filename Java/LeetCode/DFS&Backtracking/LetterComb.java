import java.util.*;

public class LetterComb {

    private static String[] dict = new String[]{"", "", "abc", "def", "ghi", "jkl",
                        "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        List<String> res = new LinkedList<>();
        if (digits == null || digits.length() <= 0) {
            return res;
        }
        dfs(digits, 0, "", res);
        return res;

    }

    private void dfs(String digits, int index, String curr, List<String> res) {
        if (curr.length() == digits.length()) {
            res.add(curr);
        }
        if (index >= digits.length()) {
            return;
        } else {
            String strForNum = dict[digits.charAt(index) - '0'];
            for (char ch : strForNum.toCharArray()) {
                dfs(digits, index + 1, curr + ch, res);
            }
        }
    }

    public static void main(String[] args) {
        LetterComb lc = new LetterComb();
        System.out.println(lc.letterCombinations("23"));
        System.out.println(lc.letterCombinations("439"));
    }


}
