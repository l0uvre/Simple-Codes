/** LC 46 -- backtracking ***/
import java.util.*;
public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, res, new ArrayList<>());
        return res; 
    }

    private void dfs(int[] nums, List<List<Integer>> res, 
            List<Integer> perm) {
        if (perm.size() == nums.length) {
            res.add(new ArrayList<>(perm));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!perm.contains(nums[i])) {
                perm.add(nums[i]);
                dfs(nums, res, perm);
                perm.remove(perm.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Permutations sol = new Permutations(); 
        int[] nums = new int[]{1, 2, 3};
        System.out.println(sol.permute(nums));
    }


}
