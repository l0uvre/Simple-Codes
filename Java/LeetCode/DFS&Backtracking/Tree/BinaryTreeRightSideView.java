/** LC199 --- Tree, Recursion, BFS**/
import java.util.*;
public class BinaryTreeRightSideView {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> rightNums = new ArrayList<>(); 
        rightFirstTraverse(root, rightNums, 0);
        return rightNums;
    }

    private void rightFirstTraverse(TreeNode node, List<Integer> rightNums, int level) {
        if (node == null) {
            return;
        } else {
            if (level >= rightNums.size()) {
                rightNums.add(node.val);
            }
            rightFirstTraverse(node.right, rightNums, level + 1);
            rightFirstTraverse(node.left, rightNums, level + 1);
        }
    }

}
