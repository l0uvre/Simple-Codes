// Leetcode 14 String
public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        } else if (strs.length == 1) {
            return strs[0];
        }    

        String first = strs[0];
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < first.length(); j++) {
            for (int i = 1; i < strs.length; i++) {
                String curr = strs[i]; 
                if (j >= curr.length() || curr.charAt(j) != first.charAt(j)) {
                    return sb.toString();
                } 
            }
            sb.append(first.charAt(j));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"flower", "flow", "flight"};
        LongestCommonPrefix lcp = new LongestCommonPrefix();
        System.out.println(lcp.longestCommonPrefix(strs));

        strs = new String[]{"dog","racecar","car"};
        System.out.println(lcp.longestCommonPrefix(strs));

    }

}
