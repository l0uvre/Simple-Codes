/**LC 727 -- String, Sliding Window. */
public class MinWindowSubsequence {

    /** Apply the sliding window approach, every time 
    we find a valid substring in s1, we shrink the window by walking
    the pointer from right to left; 
    Set the right pointer to left + 1 to test other possible valid substrings. */
    public String minWindow(String s1, String s2) {
        if (s1 == null || s1.length() == 0 || s2 == null ||
            s2.length() == 0) {
            return "";
        } 

        int minLen = Integer.MAX_VALUE;
        String res = "";
        int left = 0;
        int right = 0;

        int m = s1.length();
        int n = s2.length();

        while (right < m) {
            int pointerS2 = 0;
            /** push the right pointer to find a valid substring in s1. */
            for (; right < m; right++) {
                if (s1.charAt(right) == s2.charAt(pointerS2)) {
                    pointerS2++;
                }
                if (pointerS2 == n) {
                    break;
                }
            }
            /** failed to find a valid window for s2; then stop and return the res. */
            if (pointerS2 != n) {
                return res;
            }
            
            /** find the min window for s2 by walking left pointer to the left. */
            pointerS2 = n - 1;
            for (left = right; left >= 0; left--) {
                if (s1.charAt(left) == s2.charAt(pointerS2)) {
                    pointerS2--;
                }
                if (pointerS2 < 0) {
                    break;
                }
            }

            /** update the result variable. */
            if (right - left + 1 < minLen) {
                minLen = right - left + 1;
                res = s1.substring(left, right + 1);
            }

            right = left + 1;
        }
        return res;

    }        
}
