/*** LC 77 --  backtracking ***/
import java.util.*;
public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(n, 1, new ArrayList<>(), k, res);
        return res;
    }
    
    private void dfs(int n, int i, List<Integer> comb, int k,
            List<List<Integer>> res) {
        if (k == 0) {
            res.add(new ArrayList<>(comb)); 
        } else if (k < 0) {
            return;
        }

        for (int j = i; j <= n; j++) {
            comb.add(j);
            dfs(n, j + 1, comb, k - 1, res);
            comb.remove(comb.size() - 1);
        }
    }

    public static void main(String[] args) {
        Combinations sol = new Combinations();
        System.out.println(sol.combine(4, 2));
        System.out.println(sol.combine(1, 1));
    }

}
