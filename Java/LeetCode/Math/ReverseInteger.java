//Math Leetcode 7
//https://leetcode.com/problems/reverse-integer/
public class ReverseInteger {

    public int reverse(int x) {
        
        long rev = 0;
        while (x != 0) {
            rev = rev * 10 + x % 10;
            x /= 10;
        }
        
        if (rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE) {
            return 0;
        } else {
            return (int) rev;
        }
        
    }

    public static void main(String[] args) {
        ReverseInteger ri = new ReverseInteger();
        System.out.println(ri.reverse(123));
        System.out.println(ri.reverse(-123));
        System.out.println(ri.reverse(120));
    }
}
