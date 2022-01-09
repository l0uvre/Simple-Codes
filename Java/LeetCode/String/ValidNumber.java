/** LC 65 -- String. **/
public class ValidNumber {

    /** O(N), use three flags and update the status 
     * scanning from left to right. **/
    public boolean isNumber(String s) {
        s = s.trim();
        boolean hasNumber = false;
        boolean hasE = false;
        boolean hasDot = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                hasNumber = true;
            } else if (s.charAt(i) == '.') {
                if (hasE || hasDot) {
                    return false;
                }
                hasDot = true;
            } else if (s.charAt(i) == 'e' || s.charAt(i) == 'E') {
                if (!hasNumber || hasE) {
                    return false;
                } else {
                    hasE = true;
                    hasNumber = false;
                }
            } else if (s.charAt(i) == '-' ||
                    s.charAt(i) == '+') {
                if (i != 0 && s.charAt(i - 1) != 'e'
                        && s.charAt(i - 1) != 'E') {
                    return false;
                } 
            } else {
                return false;
            }
        }
        return hasNumber;
    }

    public static void main(String[] args) {
        ValidNumber sol = new ValidNumber(); 
        String[] strs = new String[]{"2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"};
        for (String str : strs) {
            System.out.println(sol.isNumber(str));
        }

        strs = new String[]{"abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"};
        for (String str : strs) {
            System.out.println(sol.isNumber(str));
        }

    }
}
