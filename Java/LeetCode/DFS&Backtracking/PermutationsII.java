import java.util.*;

class PermutationsII {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        boolean[] visited = new boolean[nums.length];
        Arrays.sort(nums);
        backtracking(res, new LinkedList<>(), nums, visited);
        return res;
    }
    
    private void backtracking(List<List<Integer>> res, List<Integer> curr, int[] nums, boolean[] visited) {
        if (curr.size() == nums.length) {
            res.add(new LinkedList<>(curr));
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                continue;
            }
            if (!visited[i]) {
                visited[i] = true;
                curr.add(nums[i]);
                backtracking(res, curr, nums, visited);
                visited[i] = false;
                curr.remove(curr.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        PermutationsII sol = new PermutationsII();
        int[] nums = {1, 1, 2};
        System.out.println(sol.permuteUnique(nums));
    }
}
