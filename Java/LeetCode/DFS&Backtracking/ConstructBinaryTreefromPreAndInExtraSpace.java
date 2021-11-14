/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
public class ConstructBinaryTreefromPreAndInExtraSpace {
    
    private int indexOfRootIn(int[] inorder, int root) {
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == root) {
                return i;
            }
        }
        return -1;
    }

    private int[] leftPreorder(int[] preorder, int n) {
        int[] nPreorder = new int[n];
        for (int i = 1; i <= n; i++) {
            nPreorder[i - 1] = preorder[i];
        }
        return nPreorder;
    } 
    private int[] rightPreorder(int[] preorder, int n) {
        int[] nPreorder = new int[n];
        for (int i = 0; i < n; i++) {
            nPreorder[i] = preorder[i + (preorder.length - n)];
        }
        return nPreorder;
    }

    private int[] leftInorder(int[] inorder, int rootIndex) {
        int[] nInorder = new int[rootIndex];
        for (int i = 0; i < rootIndex; i++) {
            nInorder[i] = inorder[i];
        }
        return nInorder;

    }

    private int[] rightInorder(int[] inorder, int rootIndex) {
        int[] nInorder = new int[inorder.length - rootIndex - 1];
        for (int i = 0; i < nInorder.length; i++) {
            nInorder[i] = inorder[i + rootIndex + 1];
        }
        return nInorder;
    }


    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int root = preorder[0];
        TreeNode r = new TreeNode(root);
        int rootIndexAtIn = indexOfRootIn(inorder, root);
        int numOfLeft = rootIndexAtIn;
        int numOfRight = inorder.length - numOfLeft - 1;
        if (numOfLeft > 0) {
            r.left = buildTree(leftPreorder(preorder, numOfLeft), leftInorder(inorder, rootIndexAtIn));
        }

        if (numOfRight > 0) {
            r.right = buildTree(rightPreorder(preorder, numOfRight), rightInorder(inorder, rootIndexAtIn));
        }
        return r;
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
