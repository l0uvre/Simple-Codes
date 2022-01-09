/** LC 266 -- String, HashTable. **/
public class PalindromePermutation {
    /** count the number of characters which occur odd number
     * of times if it's larger than 1 it's false. **/
    public boolean canPermutePalindrome(String s) {
        /** s only contains lowercase chars. **/
        int[] occur = new int[26];
        for (char ch : s.toCharArray()) {
            occur[ch - 'a'] += 1;
        }

        int count = 0;
        for (int i = 0; i < occur.length; i++) {
            if (occur[i] % 2 == 1) {
                count += 1;
            }
        }
        return count <= 1;
    }

    public static void main(String[] args) {
        PalindromePermutation sol = new PalindromePermutation(); 
        String s = "code";
        System.out.println(sol.canPermutePalindrome(s));

        s = "aab";
        System.out.println(sol.canPermutePalindrome(s));
        
        s = "carerac";
        System.out.println(sol.canPermutePalindrome(s));
    }
}
