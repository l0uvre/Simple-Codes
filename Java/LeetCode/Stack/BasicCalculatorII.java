/** LC 227 -- Stack ***/
import java.util.*;

public class BasicCalculatorII {
    
    /** Use stack to simulate the process 
     * before we add a num to the stack, check the sign before it,
     * the default sign is '+';
     * '+' : push(num);
     * '-' : push(-num);
     * '* /' : push(pop() * or / num). **/
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        } else {
            /** the first char maybe a '-' sign,
             * and we need a sign for the first num. **/
            char sign = '+';
            Deque<Integer> stack = new LinkedList<>();
            for (int i = 0; i < s.length(); i++) {
                /** consume space **/
                if (s.charAt(i) == ' ') {
                    continue;
                }
                if (!Character.isDigit(s.charAt(i))) {
                    sign = s.charAt(i);
                } else {
                    int num = 0;
                    while (i < s.length() && Character.isDigit(s.charAt(i))) {
                        num = num * 10 + Character.getNumericValue(s.charAt(i));
                        i++;
                    }
                    /** set it to the last digit of num, as we would increment i
                     * at the last step**/
                    i--; 
                    if (sign == '+') {
                        stack.push(num);
                    } else if (sign == '-') {
                        stack.push(-num);
                    } else if (sign == '*') {
                        stack.push(stack.pop() * num);
                    } else {
                        stack.push(stack.pop() / num);
                    }
                }
            }
            int res = 0;
            while (!stack.isEmpty()) {
                res += stack.pop();
            }
            return res;
        }
    }

    public static void main(String[] args) {
        String exp = " 3 + 42 /  6 + 3";
        BasicCalculatorII cal = new BasicCalculatorII();
        System.out.println(cal.calculate(exp));
    
    }


}
