/** LC 49 --- String, HashTable***/
import java.util.*;

public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> dict = new HashMap<>();
        List<List<String>> res = new ArrayList<>();

        for (String str : strs) {
            char[] charArr = str.toCharArray();
            Arrays.sort(charArr); // sort the characters. "Anagrams" share the same sorted one.
            String key = new String(charArr);
            dict.putIfAbsent(key, new ArrayList<>());
            dict.get(key).add(str);
        }
        res.addAll(dict.values());
        return res;
    }

    public static void main(String[] args) {
        GroupAnagrams sol = new GroupAnagrams();
        String[] strs = new String[]{"eat","tea","tan","ate","nat","bat"};
        System.out.println(sol.groupAnagrams(strs));
        strs = new String[]{""};
        System.out.println(sol.groupAnagrams(strs));
        strs = new String[]{"a"};
        System.out.println(sol.groupAnagrams(strs));
    }
}
