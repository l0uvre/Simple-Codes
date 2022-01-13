/** LC 224 -- Stack . **/
import java.util.*;
public class BasicCalculator {

    /** sign variable : default is 1;
     * result variable : the value
     * scan the string from left to right;
     * parse every number in the string and add it to the result;
     * when we get a '(', we push the result to the stack and the sign
     * before '(';
     * when we get a ')', we set 
     * result = result * prevSign(stack.pop) + stack.pop(). **/
    public int calculate(String s) {
        Deque<Integer> stack = new LinkedList<>();
        int len = s.length();
        int sign = 1;
        int result = 0;
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                int num = 0;
                while (Character.isDigit(ch)) {
                    num = num * 10 + ch - '0';
                    i++;
                    if (i == len) {
                        break;
                    }
                    ch = s.charAt(i);
                }
                i--;
                result += num * sign;
            } else if (ch == '+') {
                sign = 1;
            } else if (ch == '-') {
                sign = -1;
            } else if (ch == '(') {
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            } else if (ch == ')') {
                /** first poped is the sign before the '('. **/
                result = result * stack.pop() + stack.pop();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        BasicCalculator sol = new BasicCalculator(); 
        String s = "1 + 1";
        System.out.println(sol.calculate(s)); 

        s = "  2-1 + 2 ";
        System.out.println(sol.calculate(s)); 
        
        s = "(1+(4+5+2)-3)+(6+8)";
        System.out.println(sol.calculate(s)); 
    }
}
