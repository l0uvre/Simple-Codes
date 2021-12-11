/** LC 791 --- HashTable, String **/
import java.util.*;
public class CustomSortString {

    /** map characters to indices and sort a char array using a
     * custom comparator with the map. **/
    public String customSortString(String order, String s) {
        int[] indices = new int[26];
        Arrays.fill(indices, order.length());
        for (int i = 0; i < order.length(); i++) {
            char ch = order.charAt(i);
            indices[ch - 'a'] = i;
        }

        Character[] chars = new Character[s.length()];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = s.charAt(i);
        }
        Arrays.sort(chars, (a, b) -> {
            return indices[a - 'a'] - indices[b - 'a'];
        });

        char[] sortedChars = new char[s.length()];
        for (int i = 0; i < chars.length; i++) {
            sortedChars[i] = chars[i];
        }

        return new String(sortedChars);
    }

    public static void main(String[] args) {
        CustomSortString sol = new CustomSortString(); 
        String order = "cba";
        String s= "abcd";
        System.out.println(sol.customSortString(order, s));

        order = "cbafg";
        s = "abcd";
        System.out.println(sol.customSortString(order, s));
    }
}
