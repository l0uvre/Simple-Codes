import java.util.*;

class ValidParentheses {
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        } else if (s.length() == 1) {
            return false;
        }
        
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if (curr == '(' || curr == '[' || curr == '{') {
                stack.push(curr);
            } else {
                if (stack.empty()) {
                    return false;
                }
                char top = stack.pop();
                 if (curr == ')') {
                     if (top != '(') {
                         return false;
                     }
                 } else if (curr == ']') {
                     if (top != '[') {
                         return false;
                     }
                 } else if (curr == '}') {
                     if (top != '{') {
                         return false;
                     }
                 }
            }
        }
        
        return stack.empty();
    }

    public static void main(String[] args) {
        ValidParentheses sol = new ValidParentheses();
        System.out.println(sol.isValid("((("));
        System.out.println(sol.isValid("}])"));
        System.out.println(sol.isValid("(((){}[{}]))"));
    }
}
