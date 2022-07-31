/** LC 1347 -- String, Counting, Hash Table. **/
public class MinNumMakeTwoStringsAnagram {
    /** firstly, count the occurences of characters in each string 
     * use one counting array, increase the values for characters in String s,
     * decrease the values for characters in String t;
     *
     * secondly, loop over all the characters in String s and check our counting
     * array, if the value for a character is postive, we increase our res by 1. **/
    public int minSteps(String s, String t) {
        int[] counts = new int[26];
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'a'] += 1;
            counts[t.charAt(i) - 'a'] -= 1;
        }
        for (int count : counts) {
            if (count > 0) {
                res += count;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        MinNumMakeTwoStringsAnagram sol = new 
            MinNumMakeTwoStringsAnagram();
        String s = "bab";
        String t = "aba";
        System.out.println(sol.minSteps(s, t));

        s = "leetcode";
        t = "practice";
        System.out.println(sol.minSteps(s, t));

        s = "anagram";
        t = "mangaar";
        System.out.println(sol.minSteps(s, t));
    }
}
