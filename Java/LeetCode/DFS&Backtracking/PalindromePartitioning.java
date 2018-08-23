import java.util.*;

class PalindromePartitioning {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new LinkedList<>();
        if (s == null || s.length() == 0) {
            return res;
        }
        backtracking(res, new LinkedList<>(), s, 0); 
        return res;
    }
    
    private void backtracking(List<List<String>> res, List<String> curr, String s, int index) {
        if (index >= s.length()) {
            res.add(new LinkedList<>(curr));
        }
        for (int i = index + 1; i <= s.length(); i++) {
            String sub = s.substring(index, i);
            if (isPalindrome(sub)) {
                curr.add(sub);
                backtracking(res, curr, s, i);
                curr.remove(curr.size() - 1);
            }
        }
    }
    
    private boolean isPalindrome(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        PalindromePartitioning  sol = new PalindromePartitioning();
        System.out.println(sol.partition("aabb"));
    }
}
