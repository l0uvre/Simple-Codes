class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new LinkedList<>();
        if (n < k) {
            return res;
        }
        
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }
        backtrack(res, new LinkedList<>(), nums, 0, k);
        return res;
        
    }
    
    private void backtrack(List<List<Integer>> res, List<Integer> curr, int[] nums, int index, int k) {
        if (k < 0) {
            return;
        } else if (k == 0) {
            res.add(new LinkedList<>(curr));
        } else {
            int end = nums.length - k + 1; // attention!
            for (int i = index; i < end; i++) {
                curr.add(nums[i]);
                backtrack(res, curr, nums, i + 1, k - 1);
                curr.remove(curr.size() - 1);
            }
        }
        
    }
}
