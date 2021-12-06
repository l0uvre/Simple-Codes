import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
       this.val = val;
       this.left = left;
       this.right = right;
    }

    public String toString() {
        return Integer.toString(val);
    }

    /***
     * TODO: Write A LeverOrderTraversal to beautifully print the tree
     *      Use BFS and List to print level by level; 
     * public static List<String> levelOrderTraverse(TreeNode root);
     * 
     * **/

    public static void preOrderTra(TreeNode root) {
        if (root == null) {
            //System.out.print("null ");
            return;
        }
        System.out.print(root.val + " ");
        preOrderTra(root.left);
        preOrderTra(root.right);
    }

    /** [1,2,3,null,5,null, 4, 5, 5, 4, 4] **/
    public static TreeNode mkTree(String str) {
        /**Process string input and initialize nodes.***/
        String[] vals = str.substring(1, str.length() - 1).split(",\\s*");

        TreeNode[] nodes = new TreeNode[vals.length]; 
        for (int i = 0; i < nodes.length; i++) {
            if (!vals[i].equals("null")) {
                nodes[i] = new TreeNode(Integer.valueOf(vals[i]));
            } else {
                nodes[i] = null;
            }
        }

        /** record whether nodes[i] has been marked
         * as some node's child, if so, add i to the list
         * **/
        List<Integer> marked = new ArrayList<>();
        marked.add(0);
        for (int i = 0; i < nodes.length - 2; i++) {
            TreeNode node = nodes[i];
            if (node == null) continue;
            int leftIndex = marked.get(marked.size() - 1) + 1;
            if (leftIndex >= nodes.length) {
                break;
            }
            node.left = nodes[leftIndex];
            marked.add(leftIndex);

            int rightIndex = leftIndex + 1;

            if (rightIndex >= nodes.length) {
                break;
            }
            node.right = nodes[rightIndex];
            marked.add(rightIndex);
        }
        return nodes[0];
    }

    public static void main(String[] args) {
        TreeNode root = mkTree("[1,2,3,null,5,null,4]");
        preOrderTra(root);
        System.out.println();

        root = mkTree("[1,2,3,null,5,null, 4, 6, 7, 9, 8,10]");
        preOrderTra(root);
        System.out.println();

        root = mkTree("[3,9,20,null,null,15,7]");
        preOrderTra(root);
        System.out.println();

        root = mkTree("[3,5,1,6,2,0,8,null,null,7,4]");
        preOrderTra(root);
        System.out.println();

        root = mkTree("[5,4,8,11,null,13,4,7,2,null,null,5,1]");
        preOrderTra(root);
        System.out.println();

    }

}

