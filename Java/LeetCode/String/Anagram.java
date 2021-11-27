import java.util.*;

/*** LC 438 -- Sliding Window***/
class Anagram {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new LinkedList<>();
        if (s == null || p == null || s.length() < p.length()) {
            return res;
        }
        int left = 0, right = 0, count = 0;
        char[] arr = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : p.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        
        for (right = 0; right < s.length(); right++) {
            int ocurr = map.getOrDefault(arr[right], 0) - 1;
            map.put(arr[right], ocurr);

            if (ocurr >= 0) {
                count++;
            }
            
            while (count == p.length()) {
                if (right - left + 1 == p.length()) {
                    //System.out.println(left + " " + right);
                    res.add(left);
                }
                ocurr = map.get(arr[left]);
                map.put(arr[left], ocurr + 1);
                left++;
                if (ocurr + 1 > 0) {
                    count--;
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {
        Anagram sol = new Anagram();
        System.out.println(sol.findAnagrams("baa", "aa"));
        System.out.println(sol.findAnagrams("abab", "ab"));
    }
}
