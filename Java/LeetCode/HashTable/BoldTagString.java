/** LC 616 -- String, HashTable. **/
public class BoldTagString {

    /** O(len(s) * sum(len(word))), 
     * Add an extra boolean[] marks array;
     * for i in len(s): 
     *    we check whether s[i:] contains a word in words;
     *    if so, we mark all of marks[i : i + len(words)] with true;
     * then, we iterate the marks, add the bold tag if the 
     * marks[i] changed. **/
    public String addBoldTag(String s, String[] words) {
        int n = s.length();
        boolean[] marks = new boolean[n];
        for (int i = 0; i < n; i++) {
            for (String word : words) {
                int len = word.length();
                if (i + len - 1 < n && 
                        s.substring(i, i + len).equals(word)) {
                    for (int j = i; j < i + len; j++) {
                        marks[j] = true;
                    }
                }
            }
        }

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < marks.length; i++) {
            if (marks[i] && (i == 0 || !marks[i - 1])) {
                res.append("<b>");
            }
            res.append(s.charAt(i));
            if (marks[i] && (i == n - 1 || !marks[i + 1])) {
                res.append("</b>");
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        BoldTagString sol = new BoldTagString(); 
        String s = "abcxyz123";
        String[] words = new String[]{"abc","123"};
        System.out.println(sol.addBoldTag(s, words));

        s = "aaabbcc";
        words = new String[]{"aaa","aab","bc"};
        System.out.println(sol.addBoldTag(s, words));
    }
}
