/** LC 1429 -- Stack, String **/
import java.util.*;

class MinRemove {
    /** another approach:
     * scan from left to right, record the indices of
     * closed parenthesis without open ones ahead of them.
     * then scan from right to left, record the indices of
     * open parenthesis without closed ones to the right of 
     * them **/
    public String minRemoveToMakeValid(String s) {
        int openAvail = 0;
        Set<Integer> skipSet = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (openAvail == 0) {
                    skipSet.add(i);
                } else {
                    openAvail -= 1;
                }
            } else if (s.charAt(i) == '(') {
                openAvail += 1;
            }
        }

        int closedAvail = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ')') {
                closedAvail += 1;
            } else if (s.charAt(i) == '(') {
                if (closedAvail == 0) {
                    skipSet.add(i);
                } else {
                    closedAvail -= 1; 
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!skipSet.contains(i)){
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }


    public String minRemoveToMakeValid(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        /** Use stack to record the index of
         * parenthesis ***/
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
