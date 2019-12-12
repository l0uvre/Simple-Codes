// Leetcode 424
import java.util.*;
public class LongestRepeatingCharacterReplacement {

    public int characterReplacement(String s, int k) {
        int maxLenRep = 0;
        if (s == null || s.length() == 0) {
            return maxLenRep;
        } else if (s.length() < k) {
            return s.length();
        }
        int left = 0, right = 0;
        int maxCount = 0;
        Map<Character, Integer> frequency = new HashMap<>();
        for (; right < s.length(); right++) {
            char ch = s.charAt(right);
            frequency.put(ch, frequency.getOrDefault(ch, 0) + 1);
            maxCount = Math.max(frequency.get(ch), maxCount);
            int replacement = right - left + 1 - maxCount; 
            if (replacement <= k) {
                maxLenRep = Math.max(maxLenRep, right - left + 1);
            } else {
                char chLeft = s.charAt(left);
                if (maxCount == frequency.get(chLeft)) {
                    maxCount--; 
                }
                frequency.put(chLeft, frequency.get(chLeft) - 1); 
                left++;
            }
        }
        return maxLenRep;
    }

    public static void main(String[] args) {
        LongestRepeatingCharacterReplacement lrcr = new LongestRepeatingCharacterReplacement();
        String s = "ABAB";
        int k = 2;
        System.out.println(lrcr.characterReplacement(s, k));

        s = "AABABBA";
        k = 1;
        System.out.println(lrcr.characterReplacement(s, k));

    }
}
