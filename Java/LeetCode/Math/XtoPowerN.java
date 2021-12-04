/** LC 50 -- Math, Recursion ***/
public class XtoPowerN {
    public double myPowRecursive(double x, int n) {
        if (n == 1) {
            return x;
        } else if (n == 0) {
            return 1;
        } else if (n < 0) {
            return 1 / x * myPow((1 / x), -(n + 1));
        } else {
            double half = myPow(x, n / 2);
            if (n % 2 == 0) {
                return half * half;
            } else {
                return x * half * half;
            }
        }
    }

    public double myPow(double x, int n) {
        double res = 1;
        if (n < 0) {
            x = 1 / x;
            /** deal with integer overflow ***/
            res = x; 
            n = -(n + 1);
        } 

        /** represent n in binary form 
         * 9 = 1001 x^9 = x^(2^3) * x ^(2^0),
         * bit 0 and bit 1 is 1
         * build a loop from here.
         * **/
        while (n > 0) {
            if ((n & 1) == 1) {
                res = res * x;
            }
            x = x * x;
            n = n >> 1;
        }
        return res;
    }

    public static void main(String[] args) {
        XtoPowerN sol = new XtoPowerN(); 
        System.out.println(sol.myPow(2.0, 10));
        System.out.println(sol.myPow(2.1, 3));
        System.out.println(sol.myPow(2.0, -2));
    }
}
