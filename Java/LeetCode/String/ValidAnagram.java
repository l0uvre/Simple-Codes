/** LC 242 --- String, HashTable, Sorting***/
import java.util.*;

public class ValidAnagram {

    public boolean isAnagram(String s, String t) {
        if (s == null) {
            return t == null;
        } else if (s.length() == 0) {
            return t.length() == 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        for (char ch : t.toCharArray()) {
            if (!map.containsKey(ch)) {
                return false;
            } else {
                map.put(ch, map.get(ch) - 1);
            }
        }
        int count = 0;
        for (int i : map.values()) {
            if (i != 0) {
                return false;
            }
        }
        return true;
        
    }

    public static void main(String[] args) {
        ValidAnagram sol = new ValidAnagram(); 
        String s = "anagram";
        String t = "nagaram";
        System.out.println(sol.isAnagram(s, t));

        s = "rat";
        t = "car";
        System.out.println(sol.isAnagram(s, t));

    }
}
