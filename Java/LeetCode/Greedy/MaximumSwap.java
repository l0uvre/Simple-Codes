/** LC 670 --- Math, Greedy **/
public class MaximumSwap {
    /** find the current largest digit by scanning from right to left,
     * if the current digit is smaller, record them; (leftIndex, rightIndex)
     * The two digits are candidates. **/
    public int maximumSwap(int num) {
        /** test case: num 2736; num 9746; num 9652 ***/
        char[] digits = String.valueOf(num).toCharArray();

        int largestIndex = digits.length;
        int largest = -1;

        int leftIndex = -1, rightIndex = -1;

        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] - '0' > largest) {
                largestIndex = i;
                largest = digits[i] - '0';
            } else if (digits[i] - '0' < largest) {
                leftIndex = i;
                rightIndex = largestIndex;
            }
        }

        if (leftIndex == -1) {
            return num;
        } else {
            char tmp = digits[leftIndex];
            digits[leftIndex] = digits[rightIndex];
            digits[rightIndex] = tmp;
            return Integer.valueOf(new String(digits));
        }
    }

    /** record the rightmost indices of digits;
     * scan from left to right, if there is a larger digit(from 9 to 0)
     * to the right of the current one, swap and return. **/
    public int maximumSwap(int num) {
        char[] digits = String.valueOf(num).toCharArray();
        int[] indices = new int[10];
        Arrays.fill(indices, -1);
        for (int i = 0; i < digits.length; i++) {
            indices[digits[i] - '0'] = i;
        }

        for (int i = 0; i < digits.length; i++) {
            int digit = digits[i] - '0';
            for (int j = 9; j > digit; j--) {
                /** there is a larger digit on the right. **/
                if (indices[j] > i) {
                    char tmp = digits[i];
                    digits[i] = digits[indices[j]];
                    digits[indices[j]] = tmp;
                    return Integer.valueOf(String.valueOf(digits));
                }
            }
        }
        return num;
    }

    public static void main(String[] args) {
        MaximumSwap sol = new MaximumSwap();
        int num = 2763;
        int maxSwap = sol.maximumSwap(num);
        System.out.println(String.format("num : %d, swap: %d", num, maxSwap));

        num = 9746;
        maxSwap = sol.maximumSwap(num);
        System.out.println(String.format("num : %d, swap: %d", num, maxSwap));

        num = 4321;
        maxSwap = sol.maximumSwap(num);
        System.out.println(String.format("num : %d, swap: %d", num, maxSwap));

        num = 1993;
        maxSwap = sol.maximumSwap(num);
        System.out.println(String.format("num : %d, swap: %d", num, maxSwap));

    }
}
