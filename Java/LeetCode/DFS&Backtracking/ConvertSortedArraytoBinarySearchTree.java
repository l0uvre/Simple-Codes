class ConvertSortedArraytoBinarySearchTree {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }
    
    public TreeNode sortedArrayToBST(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }
    
    private TreeNode build(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;
        TreeNode top = new TreeNode(nums[mid]);
        top.left = build(nums, left, mid - 1);
        top.right = build(nums, mid + 1, right);
        return top;
    }

    public void inOrderTravel(TreeNode root) {
       if (root == null) {
         System.out.print("null ");
         return;
       }
       System.out.print(root.val + " ");
       inOrderTravel(root.left);
       inOrderTravel(root.right);
    }

    public static void main(String[] args) {
       int[] arr = {-10, -3, 0, 5, 9}; 
       ConvertSortedArraytoBinarySearchTree sol = new ConvertSortedArraytoBinarySearchTree();
       sol.inOrderTravel(sol.sortedArrayToBST(arr));
    }
}
