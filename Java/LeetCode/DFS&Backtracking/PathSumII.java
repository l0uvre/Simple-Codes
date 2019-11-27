// Leetcode 113 Taged DFS actually Backtracking.
import java.util.*;

public class PathSumII {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new LinkedList<>();
        dfs(res, root, new LinkedList<>(), 0, sum);
        return res;
    }

    private void dfs(List<List<Integer>> res, TreeNode curr, List<Integer> path, int currSum, int sum) {
        if (curr == null) {
            return;
        } else {
            path.add(curr.val);
            currSum += curr.val;
            if (curr.left == null && curr.right == null) {
                if (currSum == sum) {
                    res.add(new LinkedList<>(path));
                } 
            } else {
                dfs(res, curr.left, path, currSum, sum);
                dfs(res, curr.right, path, currSum, sum);
            }
            path.remove(path.size() - 1);
        }
    }
    
}

