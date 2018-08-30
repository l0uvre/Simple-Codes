import java.util.*;

public class BasicCalculatorII {
    
    /** More general approach: infix to postfix(Stack) and then evaluate the postfix. **/
    public int calculate(String s) {
        int res = 0;
        if (s == null || s.length() == 0) {
            return res;
        }
        //s.replace(" ", "");
        if (s.length() == 0) {
            return res;
        }
        //System.out.println(s);
        
        int num = 0;
        char sign = '+';
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if (Character.isDigit(curr)) {
                num = num * 10 + curr - '0';
            }
            
            if ((!Character.isDigit(curr) && curr != ' ') || i == s.length() - 1) {
                if (sign == '+') {
                    stack.push(num);
                } else if (sign == '-') {
                    stack.push(-num);
                } else if (sign == '*') {
                    stack.push(stack.pop() * num);
                } else if (sign == '/') {
                    stack.push(stack.pop() / num);
                }
                sign = curr;
                num = 0;
            }
        }
        
        while (!stack.empty()) {
            res += stack.pop();
        }
        return res;
    }

    public static void main(String[] args) {
        String exp = " 3 + 42 /  6 + 3";
        BasicCalculatorII cal = new BasicCalculatorII();
        System.out.println(cal.calculate(exp));
    
    }


}
