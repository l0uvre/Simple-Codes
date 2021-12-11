/** Leetcode 8 String . **/
public class StringToInteger {

    /** just scan the characters from left to right
     * skip leading characters, parse the sign and number. **/
    public int myAtoi(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        } else {
            int res = 0;
            /** the default integer is positive. **/
            int sign = 1;
            int i = 0;
            while (i < str.length()) {
                /** skip leading white space. **/
                while(i < str.length() && str.charAt(i) == ' ') {
                    i++;
                }

                if (i >= str.length()) {
                    break;
                }

                /** get the sign if any. **/
                if (str.charAt(i) == '-') {
                    sign = -1;
                    i++;
                } else if (str.charAt(i) == '+') {
                    sign = 1;
                    i++;
                } else if (!Character.isDigit(str.charAt(i))) {
                    /** if there are some 
                     * non digit chars before the number. **/
                    break;
                }
                /** if there is no chars left. **/
                if (i >= str.length()) {
                    break;
                }
                char ch = str.charAt(i);
                while (Character.isDigit(ch)) {
                    /** dealing with overflow. **/
                    if (sign == 1) {
                        long currValue = (long) res * 10 + (ch - '0');
                        if (currValue >= Integer.MAX_VALUE) {
                            return Integer.MAX_VALUE;
                        }
                        res = (int) currValue;
                    } else if (sign == -1) {
                        long currValue = (long) res * 10 - (ch - '0');
                        if (currValue <= Integer.MIN_VALUE) {
                            return Integer.MIN_VALUE;
                        } else {
                            res = (int) currValue;
                        }
                    }
                    i++;
                    if (i >= str.length()) {
                        break;
                    }
                    ch = str.charAt(i);
                }
                break;
            }
            return res;
        }
    }

    public static void main(String[] args) {
        StringToInteger sti = new StringToInteger();
        System.out.println(sti.myAtoi("42"));
        System.out.println(sti.myAtoi("    -42"));
        System.out.println(sti.myAtoi("42356 with"));
        System.out.println(sti.myAtoi("with 29384"));
        System.out.println(sti.myAtoi("-2319438593588458"));
        System.out.println(sti.myAtoi("-+2"));
        System.out.println(sti.myAtoi("+2319438593588458"));
    }

}

