/*** LC 125 -- Two Pointers ***/
public class ValidPalindrome {

    /** Use two pointers, left and right,
     * skip non-letter and non-digit chars,
     * convert letters to lower cases,
     * campare the chars **/
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            char leftCh = s.charAt(left);
            char rightCh = s.charAt(right);
            while (left < right && !Character.isLetterOrDigit(leftCh)) {
                left++;
                leftCh = s.charAt(left);
            }

            while (left < right && !Character.isLetterOrDigit(rightCh)) {
                right--;
                rightCh = s.charAt(right);
            }
            
            if (Character.isLetter(leftCh)) {
                leftCh = Character.toLowerCase(leftCh);
            }
                        
            if (Character.isLetter(rightCh)) {
                rightCh = Character.toLowerCase(rightCh);
            }

            if (leftCh != rightCh) {
                return false;
            } else {
                left++;
                right--;
            }
        }
        return true; 
    }

    public static void main(String[] args) {
        ValidPalindrome sol = new ValidPalindrome(); 
        String s = "A man, a plan, a canal: Panama";
        System.out.println(sol.isPalindrome(s));

        s = "race a car";
        System.out.println(sol.isPalindrome(s));

        s = "  ";
        System.out.println(sol.isPalindrome(s));
    }
}
