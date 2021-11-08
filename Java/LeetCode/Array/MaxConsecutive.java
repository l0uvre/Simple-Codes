// LC 485
public class MaxConsecutive {

    public int findMaxConsecutiveOnes(int[] nums) {
        int res = 0;
        int count = 0;
        for (int num : nums) {
            count = num == 1 ? count + 1 : 0;
            res = Integer.max(count, res);
        }

        return res;
    }

    public static void main(String[] args) {
        MaxConsecutive mc = new MaxConsecutive();
        int[] nums = new int[]{1, 1, 0, 1, 1, 1};
        System.out.println(mc.findMaxConsecutiveOnes(nums));
    }

}
