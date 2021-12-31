/** LC 216 --- DFS/Backtracking. ***/

import java.util.*;

public class CombinationSumIII {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        backtracking(1, new ArrayList<>(), 0, k, n, res);
        return res;
    }

    private void backtracking(int start, List<Integer> comb,
          int currSum, int k, int n, List<List<Integer>> res) {
        if (currSum > n || k < 0) {
            return;
        } else if (currSum == n && k == 0) {
            res.add(new ArrayList<>(comb));
            return;
        }
        for (int i = start; i < 10; i++) {
            comb.add(i);
            backtracking(i + 1, comb, currSum + i, k - 1, n, res);
            comb.remove(comb.size() - 1);
        }
    }

    public static void main(String[] args) {
        CombinationSumIII sol = new CombinationSumIII(); 
        int k = 3;
        int n = 7;
        System.out.println(sol.combinationSum3(k, n));

        k = 3;
        n = 9;
        System.out.println(sol.combinationSum3(k, n));
    }
}
