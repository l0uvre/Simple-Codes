class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0 || target <= 0) {
            return null;
        }
        
        List<List<Integer>> res = new LinkedList<>();
        Arrays.sort(candidates); //important
        backtrack(res, new LinkedList<>(), candidates, 0, target);
        return res;
        
    }
    
    private void backtrack(List<List<Integer>> res, List<Integer> curr, int[] candidates, int index, int target) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new LinkedList<>(curr));
            return;
        }
        
        for (int i = index; i < candidates.length; i++) {
            if (i != index && candidates[i] == candidates[i - 1]) {
                continue;
            } // optimizer for this special situation. 
            curr.add(candidates[i]);
            backtrack(res, curr, candidates, i + 1, target - candidates[i]);
            curr.remove(curr.size() - 1);
        }
        
    }
}
