/** LC 39 -- DFS/Backtracking. ***/
import java.util.*;
public class CombinationSum {
    /** do a backtracking on the candidates numbers;
     * don't increment the starting index since the num can be picked 
     * multiple times;
     *
     * backtracking on arrays and strings follows a similar pattern. **/
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtracking(new ArrayList<>(), 0, 0, candidates, target, res);
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
                curr.add(candidates[i]);
                backtracking(curr, i, currSum + candidates[i], candidates,
                        target, res);
                curr.remove(curr.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        CombinationSum sol = new CombinationSum(); 
        int[] candidates = new int[]{2, 3, 6, 7};
        int target = 7;
        System.out.println(sol.combinationSum(candidates, target));

        candidates = new int[]{2, 3, 5};
        target = 8;
        System.out.println(sol.combinationSum(candidates, target));

    }
}
