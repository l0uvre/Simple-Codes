import java.util.*;

class SubsetII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        backtracking(res, new LinkedList<>(), nums, 0);
        return res;
    }
    
    private void backtracking(List<List<Integer>> res, List<Integer> curr, int[] nums,
                             int index) {
        res.add(new LinkedList<>(curr));
        for (int i = index; i < nums.length; i++) {
            if (i != index && nums[i] == nums[i - 1]) {
                continue;
            }
            curr.add(nums[i]);
            backtracking(res, curr, nums, i + 1);
            curr.remove(curr.size() - 1);
        }
    }
    public static void main(String[] args) {
       SubsetII sol = new SubsetII();
       int[] nums = new int[]{4,4,4,1,4};
       System.out.println(sol.subsetsWithDup(nums));
    }
}
