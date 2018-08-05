/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new LinkedList<>();
        if (root == null) {
            return res;
        }

        
        List<String> leftPath = binaryTreePaths(root.left);
        List<String> rightPath = binaryTreePaths(root.right);
        if (leftPath != null && leftPath.size() > 0) {
            for (String str: leftPath) {
                res.add(String.valueOf(root.val) + "->" + str);
            }
        }
        
        if (rightPath != null && rightPath.size() > 0) {
            for (String str: rightPath) {
                res.add(String.valueOf(root.val) + "->" + str);
            }
        } 
        
        if (res.isEmpty()) {
            res.add(String.valueOf(root.val));
        }
        return res;
    }
}
