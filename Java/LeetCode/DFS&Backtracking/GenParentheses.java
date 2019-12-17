// Leetcode 22 backtracking
import java.util.*;

public class GenParentheses {

    public List<String> generateParenthesis(int n) {
        List<String> res = new LinkedList<>();

        if (n <= 0) {
            return res; 
        }
        dfs(res, n, "", 0, 0);
        return res; 
    }

    private void dfs(List<String> res, int n, String tmp, int left, int right) {
        if (tmp.length() == n * 2) {
            res.add(tmp);
            return;
        } 
        if (left < n) {
            dfs(res, n, tmp + '(', left + 1, right);
        }
        if (right < left) {
            dfs(res, n, tmp + ')', left, right + 1);
                
        }
    }

    public static void main(String[] args) {
        GenParentheses gp = new GenParentheses();
        System.out.println(gp.generateParenthesis(3));
        System.out.println(gp.generateParenthesis(2));
    }

}
