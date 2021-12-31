/** LC 40 -- DFS/backtracking. **/
import java.util.*;

public class CombinationSumII {

    /** do a backtracking on the candidates numbers;
     * do increment the starting index when conducting the dfs,
     * since the num can be picked only one time;
     *
     * to avoid duplicate comb, sort the candidate array and skip the duplicate
     * ones;
     *
     * backtracking on arrays and strings follows a similar pattern. **/
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        backtracking(new LinkedList<>(), 0, 0, candidates, target, res);
        return res;
    }

    private void backtracking(List<Integer> curr, int index, int currSum, 
            int[] candidates, int target, List<List<Integer>> res) {
        if (currSum > target) {
            return;
        } else if (currSum == target) {
            res.add(new ArrayList<>(curr));
        } else {
            for (int i = index; i < candidates.length; i++) {
                if (i > index && candidates[i] == candidates[i - 1]) {
                    continue;
                }
                curr.add(candidates[i]);
                backtracking(curr, i + 1, currSum + candidates[i], candidates,
                        target, res);
                curr.remove(curr.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        CombinationSumII sol = new CombinationSumII();
        int[] candidates = new int[]{10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        System.out.println(sol.combinationSum2(candidates, target));

        candidates = new int[]{2, 5, 2, 1, 2};
        target = 5;
        System.out.println(sol.combinationSum2(candidates, target));

    }
}
