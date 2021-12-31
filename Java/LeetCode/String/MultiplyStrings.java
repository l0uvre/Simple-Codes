/*** LC 43 --- String, Math. **/
public class MultiplyStrings {

    /** the max lengh of the result is m + n;
     * build an array that calculates the value of every digit
     * from right ot left; res = [], len(res) = m + n;
     * the related indices is (i + j) and (i + j + 1) 
     * for num1[i] * num2[j];
     * add the carry to the multiple, and accumulate the value 
     * res[i + j], update res[i + j + 1];
     *
     * reference: https://leetcode.com/problems/multiply-strings/discuss/17605/Easiest-JAVA-Solution-with-Graph-Explanation. **/
    public String multiply(String num1, String num2) {
        int n1 = num1.length();
        int n2 = num2.length();
        int[] pos = new int[n1 + n2];
        for (int i = n1 - 1; i >= 0; i--) {
            for (int j = n2 - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * 
                    (num2.charAt(j) - '0');
                int p1 = i + j, p2 = i + j + 1;
                int sum = mul + pos[p2];
                
                pos[p1] += sum / 10;
                pos[p2] = sum % 10;
            }
        }
        /***
         *    123
         *     45
         *   ----
         *     15
         *    10
         *   05
         *    12
         *   08
         *  04
         *  -----
         *  05535
          **/
        StringBuilder sb = new StringBuilder();
        for (int digit : pos) {
            if (sb.length() == 0) {
                if (digit != 0) {
                    sb.append(digit);
                }
            } else {
                sb.append(digit);
            }
        }
        if (sb.length() == 0) {
            return "0";
        } else {
            return sb.toString();
        }
    }
}
