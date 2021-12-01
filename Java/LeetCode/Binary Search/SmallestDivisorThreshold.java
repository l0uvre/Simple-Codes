/*** LC 1283 --- Binary Search ***/
public class SmallestDivisorThreshold {
    public int smallestDivisor(int[] nums, int threshold) {
        int left = 1, right = (int) Math.pow(10, 6);
        while (left < right) {
            int mid = left + (right - left) / 2;
            int specialSum = 0;
            for (int num : nums) {
                /** specialSum += (num + mid - 1) / mid; ***/
                specialSum += (int) Math.ceil(num / (double) mid);
            }
            if (specialSum > threshold) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        SmallestDivisorThreshold sol = new SmallestDivisorThreshold(); 
        int[] nums = new int[]{1,2,5,9};
        int threshold = 6;
        System.out.println(sol.smallestDivisor(nums, threshold));

        nums = new int[]{44,22,33,11,1};
        threshold = 5;
        System.out.println(sol.smallestDivisor(nums, threshold));
    }
}
