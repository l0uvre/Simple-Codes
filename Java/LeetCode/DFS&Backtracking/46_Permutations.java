class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        backtrack(res, new LinkedList<>(), nums);
        return res;
    }
    
    private void backtrack(List<List<Integer>> res, List<Integer> curr, int[] nums) {
        if (curr.size() == nums.length) {
            res.add(new LinkedList<>(curr));
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (!curr.contains(nums[i])) {
                curr.add(nums[i]);
                backtrack(res, curr, nums);
                curr.remove(curr.size() - 1);
            } 
        }
    }
}
