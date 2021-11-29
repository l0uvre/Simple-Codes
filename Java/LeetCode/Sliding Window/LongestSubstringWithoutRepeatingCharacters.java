/***LC 3 -- Sliding Window ***/
import java.util.*;
public class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        } else {
            int left = 0, right = 0;
            int maxLen = 0;
            Set<Character> set = new HashSet<>();
            for (; right < s.length(); right++) {
                char ch = s.charAt(right);  
                while (set.contains(ch)) {
                    set.remove(s.charAt(left));
                    left++;
                }
                set.add(ch);
                maxLen = Math.max(maxLen, right - left + 1);
            }
            return maxLen;
        }
    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters sol =
            new LongestSubstringWithoutRepeatingCharacters(); 
        System.out.println(sol.lengthOfLongestSubstring("abaabcbb"));
        System.out.println(sol.lengthOfLongestSubstring("bbbbb"));
        System.out.println(sol.lengthOfLongestSubstring("pwwkew"));
        System.out.println(sol.lengthOfLongestSubstring(""));

    }
}
