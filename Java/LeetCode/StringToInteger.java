// Leetcode 8 String
public class StringToInteger {

    public int myAtoi(String str) {
        if (str == null || str.length() < 1) {
            return 0;
        }     
        int sign = 1;
        int res = 0;
        boolean signed = false;
        boolean whiteSkipped = false;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ' && !whiteSkipped) {
                while (i < str.length() && str.charAt(i) == ' ') {
                    i++;
                }
                whiteSkipped = true;
                if (i >= str.length()) {
                    break;
                }
            }

            if (res == 0 && !signed) {
                if (str.charAt(i) == '-') {
                    sign = -sign;
                    signed = true;
                    whiteSkipped = true;
                    continue;
                } else if (str.charAt(i) == '+') {
                    signed = true;
                    whiteSkipped = true;
                    continue;
                } 
            }

            if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                break;
            }
            
            signed = true;
            whiteSkipped = true;

            int digit = str.charAt(i) - '0';
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 
                        && digit > Integer.MAX_VALUE % 10)) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;        
            } else {
                res = res * 10 + digit;
            }
        }
        return res * sign;
    } 

    public static void main(String[] args) {
        StringToInteger sti = new StringToInteger();
        System.out.println(sti.myAtoi("42"));
        System.out.println(sti.myAtoi("    -42"));
        System.out.println(sti.myAtoi("42356 with"));
        System.out.println(sti.myAtoi("with 29384"));
        System.out.println(sti.myAtoi("-2319438593588458"));
        System.out.println(sti.myAtoi("-+2"));
    }

}

