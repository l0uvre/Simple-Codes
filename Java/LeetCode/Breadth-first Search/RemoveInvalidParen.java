/** LC 301 -- String, BFS. **/
import java.util.*;
public class RemoveInvalidParen {
    /** do a bfs on strings, for every layer, we remove every one
     * possible parenthesis, and stop on the level if
     * we find a valid one.**/
    public List<String> removeInvalidParentheses(String s) {
        /** use set to avoid duplicates. **/
        Set<String> set = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        q.offer(s);
        while (!s.isEmpty()) {
            /** use set to avoid duplicates. **/
            Set<String> nextLayer = new HashSet<>();
            for (int i = q.size(); i > 0; i--) {
                String curr = q.poll();
                if (isValid(curr)) {
                    set.add(curr);
                }
                /** in order to find the min removal valid ones, 
                 * we stop the bfs when we have some valid ones in the 
                 * current search layer. **/
                if (set.isEmpty()) {
                    for (int j = 0; j < curr.length(); j++) {
                        if (curr.charAt(j) == '(' ||
                                curr.charAt(j) == ')') {
                            nextLayer.add(curr.substring(0, j) + 
                                    curr.substring(j + 1));
                                }
                    }
                }
            }
            if (!set.isEmpty()) {
                break;
            }
            q.addAll(nextLayer);
        }
        return new ArrayList<>(set);
    }

    private boolean isValid(String s) {
        int count = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                count += 1;
            } else if (ch == ')') {
                count -= 1;
            }
            
            if (count < 0) {
                return false;
            }
        }
        return count == 0;
    }

    public static void main(String[] args) {
        RemoveInvalidParen sol = new RemoveInvalidParen(); 
        String s = "()())()";
        System.out.println(sol.removeInvalidParentheses(s));

        s = "(a)())()";
        System.out.println(sol.removeInvalidParentheses(s));

        s = ")(";
        System.out.println(sol.removeInvalidParentheses(s));
    }
}
