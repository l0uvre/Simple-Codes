//Leetcode 13 Math
import java.util.*;

public class RomanInteger {
    
    public int romanToInt(String s) {
        Map<Character, Integer> dict = new HashMap<>();
        dict.put('I', 1);
        dict.put('V', 5);
        dict.put('X', 10);
        dict.put('L', 50);
        dict.put('C', 100);
        dict.put('D', 500);
        dict.put('M', 1000);
        int prev = dict.get(s.charAt(0));
        int sum = 0;
        for (int i = 1; i < s.length(); i++) { 
            int curr = dict.get(s.charAt(i));
            if (curr > prev) {
                sum -= prev; 
            } else {
                sum += prev;
            }
            prev = curr;
        } 
        sum += prev;
        return sum;
    }

    public static void main(String[] args) {
        RomanInteger ri = new RomanInteger();
        String roman = "III";
        System.out.println(ri.romanToInt(roman));

        roman = "IV";
        System.out.println(ri.romanToInt(roman));

        roman = "IX";
        System.out.println(ri.romanToInt(roman));

        roman = "LVIII";
        System.out.println(ri.romanToInt(roman));

        roman = "MCMXCIV";
        System.out.println(ri.romanToInt(roman));
    }

}
