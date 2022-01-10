/** LC 536 -- Tree, Stack. **/
import java.util.*;
public class ConstructBinaryTreeString {
    
    /** use a stack;
     * scan the string from left to right;
     *
     * every time we process a substring of numbers,
     * we create a treeNode and peek the top of the stack;
     * if the top's left chid is null, then assign it as 
     * the left child; if the top's right child is null, 
     * then assign it as the right child; and push it to the 
     * stack;
     *
     * every time we get a ')' char, we pop on node off the
     * stack. **/
    public TreeNode str2tree(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        for (int i = 0; i < s.length();) {
            char ch = s.charAt(i);
            if (ch == '-' || (ch >= '0' && ch <= '9')) {
                int val = 0;
                int sign = 1;
                if (ch == '-') {
                    sign = -1;
                    i++;
                    ch = s.charAt(i);
                }
                while (Character.isDigit(ch)) {
                    val = val * 10 + ch - '0';
                    i++;
                    if (i >= s.length()) {
                        break;
                    }
                    ch = s.charAt(i);
                }
                
                TreeNode curr = new TreeNode(val * sign);
                if (!stack.isEmpty()) {
                    TreeNode parent = stack.peek();
                    if (parent.left == null) {
                        parent.left = curr;
                    } else {
                        parent.right = curr;
                    }
                }
                stack.push(curr);

            } else if (ch == ')') {
                stack.pop();
                i++;
            } else {
                i++;
            }
        }
        return stack.pop();
    }   
    

}
