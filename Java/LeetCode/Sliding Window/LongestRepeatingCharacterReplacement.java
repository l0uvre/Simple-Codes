/** LC 424 --- Sliding Window **/
public class LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        } else {
            int left = 0, right = 0;
            int maxCount = 0;
            int[] freq = new int[26];
            int maxLen = 0;
            for ( ; right < s.length(); right++) {
                char ch = s.charAt(right);
                freq[ch - 'A'] += 1;
                /** maxCount doesn't have to be valid all the time
                 * as we are looking for a larger window in which 
                 * maxCount of a single letter is larger.
                 * */
                maxCount = Math.max(freq[ch - 'A'], maxCount);
                int numOfReplace = right - left + 1 - maxCount;
                if (numOfReplace <= k) {
                    maxLen = Math.max(numOfReplace + maxCount, maxLen);
                } else {
                    ch = s.charAt(left);
                    freq[ch - 'A'] -= 1;
                    left++;
                }
            }
            return maxLen;
        }        
    }

    public static void main(String[] args) {
        LongestRepeatingCharacterReplacement sol =
            new LongestRepeatingCharacterReplacement(); 
        String s = "ABAB";
        int k = 4;
        System.out.println(sol.characterReplacement(s, k));

        s = "AABABBA";
        k = 1;
        System.out.println(sol.characterReplacement(s, k));
    }
}
