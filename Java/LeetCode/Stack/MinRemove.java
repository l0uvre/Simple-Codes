import java.util.*;

class MinRemove {
    public String minRemoveToMakeValid(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        Stack<Integer> stack = new Stack<>();
        Set<Integer> skipSet = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i); 
            } else if (s.charAt(i) == ')') {
                if (stack.isEmpty()) {
                    skipSet.add(i);
                } else {
                    stack.pop();
                }
            }
        }

        while(!stack.isEmpty()) {
            skipSet.add(stack.pop());
        }
        for (int i = 0; i < s.length(); i++) {
            if (!skipSet.contains(i)) {
                sb.append(s.charAt(i));        
            }
        }
        return sb.toString();
    }

}
