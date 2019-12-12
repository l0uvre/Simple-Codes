// Leetcode 3
import java.util.*;

public class LongestSubstring {
    
    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        if (s == null || s.length() == 0) {
            return res;
        }
        int left = 0, right = 0;
        Set<Character> set = new HashSet<>();
        for (; right < s.length(); right++) {
            char ch = s.charAt(right);
            if (set.contains(ch)) {
                res = Math.max(res, right - left);
                while (set.contains(ch)) {
                    char leftCh = s.charAt(left);
                    set.remove(leftCh);
                    left++;
                }
            } 
            set.add(ch);
        }
        res = Math.max(res, right - left);
        return res;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        LongestSubstring ls = new LongestSubstring();
        System.out.println(ls.lengthOfLongestSubstring(s));
        
        s = "bbbbb";
        System.out.println(ls.lengthOfLongestSubstring(s));

        s = "pwwkew";
        System.out.println(ls.lengthOfLongestSubstring(s));
    }

}
