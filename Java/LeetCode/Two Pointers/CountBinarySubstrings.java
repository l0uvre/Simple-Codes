//LC 696  Two Pointers
public class CountBinarySubstrings {
    public int countBinarySubstrings(String s) {
        int prevLength = 0;
        int currLength = 1;
        int num = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                currLength++;
            } else {
                prevLength = currLength;
                currLength = 1;
            }

            if (prevLength >= currLength) {
                num++;   
            }
            /*** if the previous 1s or 0s grouped substring is equal to or longer than current substring,
             * it means we found a valid substring  ex. 001,'01'; 0011, '0011'
             * ***/
        }
        return num;
    }

}
