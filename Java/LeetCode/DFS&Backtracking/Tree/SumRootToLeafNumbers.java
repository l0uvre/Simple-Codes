/** LC 129 -- Tree, DFS. **/
import java.util.*;

public class SumRootToLeafNumbers {

    /** I did a dfs/backtracking to get a list of numbers
     * from root to leafs. **/
    public int sumNumbers(TreeNode root) {
        List<Integer> nums = new ArrayList<>();
        dfs(root, new StringBuilder(), nums);

        return nums.stream()
            .mapToInt(Integer::intValue)
            .sum();
    }

    private void dfs(TreeNode node, StringBuilder num, List<Integer> nums) {
        if (node == null) {
            return;
        }

        num.append(node.val);
        int len = num.length();
        
        if (node.left == null && node.right == null) {
            nums.add(Integer.valueOf(num.toString()));
        } 
        dfs(node.left, num, nums);
        num.setLength(len);
        dfs(node.right, num, nums);
        num.setLength(len);
    }

}
