/*** LC 78 --- DFS, backtracking***/
import java.util.*;
public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, 0, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int[] nums, int index, List<Integer> path,
            List<List<Integer>> res) {
        res.add(new ArrayList<>(path)); 

        for (int i = index; i < nums.length; i++) {
            path.add(nums[i]);
            dfs(nums, i + 1, path, res); 
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        Subsets sol = new Subsets();
        int[] nums = new int[]{1, 2, 3};
        System.out.println(sol.subsets(nums));
    }

}
