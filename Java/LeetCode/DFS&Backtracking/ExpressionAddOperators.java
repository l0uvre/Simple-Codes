/** LC 282 --- DFS, backtracking. **/
import java.util.*;
public class ExpressionAddOperators {

    /** pick prefix nums as operands and do the dfs on the rest of the string;
     * Along the search, at every step, either add '+', '-', '*' before an operand. **/
    public List<String> addOperators(String num, int target) {
        List<String> expressions = new ArrayList<>();
        dfs(num, target, new StringBuilder(), 0, 0, 0, expressions);
        return expressions;
    }

    private void dfs(String num, int target, StringBuilder expr, 
            int index, long currVal, long prevNum, List<String> res) {

        if (index == num.length()) {
            if (currVal == (long) target) {
                res.add(expr.toString());
            }
            return;
        } 

        for (int i = index; i < num.length(); i++) {
            if (num.charAt(index) == '0' && i > index) {
                /** skip 0+36 leading zero numbers **/
                break;
            }

            long operand = Long.valueOf(num.substring(index, i + 1));
            /** saved for backtracking. **/
            int len = expr.length();
            if (index == 0) {
                /** we don't need add operators to the first number. **/
                dfs(num, target, expr.append(operand), i + 1, operand,
                        operand, res);
                expr.setLength(len);
            } else {
                dfs(num, target, expr.append('+').append(operand), i + 1, 
                        currVal + operand, operand, res);
                expr.setLength(len);

                dfs(num, target, expr.append('-').append(operand), i + 1,
                        currVal - operand, -operand, res);
                expr.setLength(len);

                dfs(num, target, expr.append('*').append(operand), i + 1,
                        currVal - prevNum + prevNum * operand, prevNum * operand, res); 
                expr.setLength(len);
            }
        }
    }
    public static void main(String[] args) {
        ExpressionAddOperators sol = new ExpressionAddOperators(); 
        String num = "123";
        int target = 6;
        System.out.println(sol.addOperators(num, target));

        num = "232";
        target = 8;
        System.out.println(sol.addOperators(num, target));

        num = "105";
        target = 5;
        System.out.println(sol.addOperators(num, target));

        num = "00";
        target = 0;
        System.out.println(sol.addOperators(num, target));
    }
}
