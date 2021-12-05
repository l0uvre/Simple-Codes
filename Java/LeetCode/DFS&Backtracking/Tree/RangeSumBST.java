/** LC 938 --- Tree, DFS ***/
public class RangeSumBST {

    /** Using recursion, just think recursively 
     * O(logN) average, O(N) worst ***/
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        } else {
            if (root.val >= low && root.val <= high) {
                int sum = 0;
                sum += root.val;
                sum += rangeSumBST(root.right, low, high);
                sum += rangeSumBST(root.left, low, high);
                return sum;
            } else if (root.val < low) {
                return rangeSumBST(root.right, low, high);
            } else {
                return rangeSumBST(root.left, low, high);
            }
        }
    }

    public static void main(String[] args) {
        RangeSumBST sol = new RangeSumBST(); 
        TreeNode root = TreeNode.mkTree("[10,5,15,3,7,null,18]");
        int low = 7, high = 15;
        System.out.println(sol.rangeSumBST(root, low, high));

        root = TreeNode.mkTree("[10,5,15,3,7,13,18,1,null,6]");
        low = 6;
        high = 10;
        System.out.println(sol.rangeSumBST(root, low, high));
    }
}
