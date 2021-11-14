// LC 105 Tree, Divide and Conquer, Recursion
public class ConstructBinaryTreefromPreAndIn {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, inorder, 0, preorder.length - 1, 
                0, inorder.length - 1);
    }
    
    private int indexOfRootIn(int[] inorder, int val, int m, int n) {
        for (int i = m; i <= n; i++) {
            if (inorder[i] == val) {
                return i;
            }
        }
        return -1;
    }


    private TreeNode buildTree(int[] preorder, int[] inorder, int preLeft, 
            int preRight, int inLeft, int inRight) {
        if (preLeft >= preorder.length || preLeft > preRight ||
                inLeft > inRight) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preLeft]);
        int rootIndex = indexOfRootIn(inorder, root.val, inLeft, inRight);
        int leftNum = rootIndex - inLeft; // number of nodes in the left sub tree.

        root.left = buildTree(preorder, inorder, preLeft + 1, preLeft + leftNum,
                inLeft, rootIndex - 1);
        root.right = buildTree(preorder, inorder, preLeft + leftNum + 1, preRight,
                rootIndex + 1, inRight);
        return root;

        
    }

    public static void main(String[] args) {
        ConstructBinaryTreefromPreAndInExtraSpace sol = new ConstructBinaryTreefromPreAndInExtraSpace();
        int[] preorder = new int[]{3, 9, 20, 15, 7};
        int[] inorder = new int[]{9, 3, 15, 20, 7};
        TreeNode newTree = sol.buildTree(preorder, inorder);
        TreeNode.preOrderTra(newTree);
        System.out.println();

    }

}
