//https://leetcode.com/problems/first-missing-positive/
// Cycle sort
class FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        } 
        for (int i = 0; i < nums.length; ) {
            int num = nums[i];
            if (num == i + 1 || !inRange(nums.length, num)) {
                i++;
            } else {
                if (nums[num - 1] == num) {
                    i++;
                } else {
                    swap(nums, i, num - 1);
                }
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    private boolean inRange(int length, int num) {
        return num > 0 && num <= length;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static void print(int[] arr) {
        for (int a : arr) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        FirstMissingPositive fmp = new FirstMissingPositive();
        int[] nums = new int[]{1, 1};
        print(nums);
        System.out.println(fmp.firstMissingPositive(nums));
        System.out.println();
        
        nums = new int[]{0, 0, 0};
        print(nums);
        System.out.println(fmp.firstMissingPositive(nums));
        System.out.println();

        nums = new int[]{1, 2, 0};
        print(nums);
        System.out.println(fmp.firstMissingPositive(nums));
        System.out.println();

        nums = new int[]{3, 4, -1, 1};
        print(nums);
        System.out.println(fmp.firstMissingPositive(nums));
        System.out.println();

        nums = new int[]{7, 8, 9, 11, 12};
        print(nums);
        System.out.println(fmp.firstMissingPositive(nums));
        System.out.println();
    }
}
