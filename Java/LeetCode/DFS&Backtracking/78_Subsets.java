/** This solution is what I firstly came up with. Not so fast, just OK.**/
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        for (int i = 0; i <= nums.length; i++) {
            backtrack(res, new LinkedList<>(), nums, i, 0);
        }
        return res;
    }
    
    private void backtrack(List<List<Integer>> res, List<Integer> curr
                           , int[] nums, int targetLen, int index) {
        if (targetLen < 0) {
            return;
        }
        if (0 == targetLen) {
            res.add(new LinkedList<>(curr));
        } else {
            for (int i = index; i < nums.length; i++) {
                curr.add(nums[i]);
                backtrack(res, curr, nums, targetLen - 1, i + 1);
                curr.remove(curr.size() - 1);
            }
        }
    }
}

/***More optimized solution. **/
class Solution2 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        backtrack(res, new LinkedList<>(), nums, 0);
        return res;
    }
    
    private void backtrack(List<List<Integer>> res, List<Integer> curr,
                           int[] nums, int index) {
        res.add(new LinkedList<>(curr));
        for (int i = index; i < nums.length; i++) {
            curr.add(nums[i]);
            backtrack(res, curr, nums, i + 1);
            curr.remove(curr.size() - 1);
        }
        
    }
}
