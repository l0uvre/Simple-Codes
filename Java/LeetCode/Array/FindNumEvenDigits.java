// LC 1295 -- Array

public class FindNumEvenDigits {
    public int findNumbers(int[] nums) {
        int result = 0;
        for (int num: nums) {
            if (getNumOfDigits(num) % 2 == 0) {
                result++;
            }
        } 
        return result;
    }

    private int getNumOfDigits(int num) {
        int digits = 0;
        while (num > 0) {
            digits++;
            num = (int) (num / 10);
        }
        return digits;
    }
}
