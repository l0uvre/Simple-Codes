// LC 238
public class ProductArrayExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        if (nums.length == 0) {
            return new int[]{0};
        }
        int[] res = new int[nums.length];
        int[] leftProduct = new int[nums.length]; // leftProduct[i] means the product of nums[0: i - 1]
        int[] rightProduct = new int[nums.length];

        leftProduct[0] = rightProduct[nums.length - 1] = 1;
        for (int i = 1; i < nums.length; i++) {
            leftProduct[i] = leftProduct[i - 1] * nums[i - 1];
            rightProduct[nums.length - 1 - i] = rightProduct[nums.length - i] * nums[nums.length - i];
        }

        for (int i = 0; i < nums.length; i++) {
            res[i] = leftProduct[i] * rightProduct[i];
        }
        return res;
    }


    public static void main(String[] args) {
        ProductArrayExceptSelf  solver = new ProductArrayExceptSelf();
        int[] nums = new int[]{1, 2, 3, 4};
        ArrayUtil.printNums(solver.productExceptSelf(nums));
        nums = new int[]{-1, 1, 0, -3, 3};
        ArrayUtil.printNums(solver.productExceptSelf(nums));
    }

}

