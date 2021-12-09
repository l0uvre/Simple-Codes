/** LC 953 --- String, HashTable **/
public class VerifyingAlienDictionary {

    /** map the characters in order to indices and compare the
     * pairs of strings in words. **/
    public boolean isAlienSorted(String[] words, String order) {
        int[] indices = new int[26];
        for (int i = 0; i < order.length(); i++) {
            char ch = order.charAt(i);
            indices[ch - 'a'] = i;
        }

        for (int i = 0; i < words.length - 1; i++) {
            if (compare(words[i], words[i + 1], indices) > 0) {
                return false;
            }
        }
        return true;
    }

    private int compare(String str1, String str2, int[] indices) {
        for (int i = 0; i < str1.length() && i < str2.length(); i++) {
            char ch1 = str1.charAt(i);
            char ch2 = str2.charAt(i);
            if (ch1 != ch2) {
                return Integer.compare(indices[ch1 - 'a'],
                        indices[ch2 - 'a']);
            }
        }
        return Integer.compare(str1.length(), str2.length());
    }

    public static void main(String[] args) {
        VerifyingAlienDictionary sol = new VerifyingAlienDictionary();  
        String[] words = new String[]{"hello","leetcode"};
        String order = "hlabcdefgijkmnopqrstuvwxyz";
        System.out.println(sol.isAlienSorted(words, order));

        words = new String[]{"word","world","row"};
        order = "worldabcefghijkmnpqstuvxyz";
        System.out.println(sol.isAlienSorted(words, order));

        words = new String[]{"apple","app"};
        order = "abcdefghijklmnopqrstuvwxyz";
        System.out.println(sol.isAlienSorted(words, order));
    }
}
