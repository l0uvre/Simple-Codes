/** LC 680 -- String, Two Pointers ***/
public class ValidPalindromeII {

    public boolean validPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                /** delete one character **/
                return isPalindrome(s, left + 1, right) ||
                        isPalindrome(s, left, right - 1);
            }
        }
        return true;
    }

    private boolean isPalindrome(String s, int left, int right) {
        if (left == right) {
            return true;
        } else {
            while (left < right) {
                if (s.charAt(left) != s.charAt(right)) {
                    return false;
                }
                left++;
                right--;
            }
            return true;
        }
    }



    public static void main(String[] args) {
        ValidPalindromeII sol = new ValidPalindromeII();
        String s = "abaab";
        System.out.println(sol.validPalindrome(s));

        s = "abca";
        System.out.println(sol.validPalindrome(s));

        s = "aba";
        System.out.println(sol.validPalindrome(s));

        s = "abc";
        System.out.println(sol.validPalindrome(s));
    }
}
