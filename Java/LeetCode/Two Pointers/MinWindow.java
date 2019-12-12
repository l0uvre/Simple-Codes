import java.util.*;
import java.io.*;

public class MinWindow {
    public String minWindow(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        int minLen = s.length() + 1, left = 0, right = 0, count = 0, minLeft = 0; 
        for (char ch: t.toCharArray()) {
            if (!map.containsKey(ch)) {
                map.put(ch, 1);
            } else {
                map.put(ch, map.get(ch) + 1);
            }
        }
        
        for (right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            int charOccur = map.getOrDefault(ch, 0);
            map.put(ch, charOccur - 1);
            
            if (map.get(ch) >= 0) {
                count++;
            }
            
            while (count == t.length()) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minLeft = left;
                }
                char leftCh = s.charAt(left);
                map.put(leftCh, map.get(leftCh) + 1);
                if (map.get(leftCh) > 0) {
                    count--;
                }
                left++;
            }
            
        }
        return minLen < s.length() + 1 ? s.substring(minLeft, minLeft + minLen): "";
    }
    public static void main(String[] args) {
        MinWindow sol = new MinWindow();
        System.out.println(sol.minWindow("ADOBECODEBANC", "ABC"));
    }
    
}
