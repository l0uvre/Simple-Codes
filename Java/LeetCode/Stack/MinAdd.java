/*** LC 921 --- Stack **/
import java.util.*;
public class MinAdd {
    /** Like we did in LC 1249, we can scan from
     * left to right and right to left 
     *
     * Or, we can use stack  **/
    public int minAddToMakeValid(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Deque<Character> stack = new LinkedList<>();
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                if (stack.isEmpty() || 
                        stack.peek() != '(') {
                    stack.push(ch);
                } else {
                    stack.pop();
                }
            }
        }
        return stack.size();
    }

    /** two scan ***/
    public int minAddToMakeValidTwoScan(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        } else {
            int minAdd = 0;
            int numOfOpen = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    numOfOpen += 1;
                } else {
                    if (numOfOpen == 0) {
                        minAdd += 1;
                    } else {
                        numOfOpen -= 1;
                    }
                }
            }
            int numOfClosed = 0;
            for (int i = s.length() - 1; i >= 0; i--) {
                if (s.charAt(i) == ')') {
                    numOfClosed += 1;
                } else {
                    if (numOfClosed == 0) {
                        minAdd += 1;
                    } else {
                        numOfClosed -= 1;
                    }
                }
            }
            return minAdd;
        }
    }

    public static void main(String[] args) {
        MinAdd sol = new MinAdd();
        String s = "())";
        System.out.println(sol.minAddToMakeValid(s));
        System.out.println(sol.minAddToMakeValidTwoScan(s));

        s = "(((";
        System.out.println(sol.minAddToMakeValid(s));
        System.out.println(sol.minAddToMakeValidTwoScan(s));

        s = "()";
        System.out.println(sol.minAddToMakeValid(s));
        System.out.println(sol.minAddToMakeValidTwoScan(s));

        s = "(";
        System.out.println(sol.minAddToMakeValid(s));
        System.out.println(sol.minAddToMakeValidTwoScan(s));

        s = ")";
        System.out.println(sol.minAddToMakeValid(s));
        System.out.println(sol.minAddToMakeValidTwoScan(s));
        
        s = "()))((";
        System.out.println(sol.minAddToMakeValid(s));
        System.out.println(sol.minAddToMakeValidTwoScan(s));
    }
}
