/*** LC 249 --- HashTable. ***/
import java.util.*;
public class GroupShiftedStrings {

    /** shift the string so that it starts with 'a'.**/
    private String shiftFirstToA(String s) {
        char[] shifted = s.toCharArray();
        /** the shift length **/
        int diff = shifted[0] - 'a'; 
        for (int i = 0; i < shifted.length; i++) {
            /** dealing with overflow **/
            shifted[i] = (char) ((shifted[i] - 'a' - diff + 26 ) % 26 + (int) 'a');
        }
        return String.valueOf(shifted);
    }

    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> dict = new HashMap<>();
        for (String str : strings) {
            String strShifted = shiftFirstToA(str);
            dict.putIfAbsent(strShifted, new ArrayList<>());
            dict.get(strShifted).add(str);
        }
        List<List<String>> res = new ArrayList<>(dict.values());
        return res; 
    }

    public static void main(String[] args) {
        GroupShiftedStrings sol = new GroupShiftedStrings(); 
        String[] strings = new String[]{
            "abc","bcd","acef","xyz","az","ba","a","z"
        };
        System.out.println(sol.groupStrings(strings));

        strings = new String[]{"a"};
        System.out.println(sol.groupStrings(strings));
    }
}
