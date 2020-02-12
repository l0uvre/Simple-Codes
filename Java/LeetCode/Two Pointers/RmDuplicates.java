// LC 26 Array
public class RmDuplicates {

    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }    
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }


    private static void print(int[] nums, int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(nums[i] + " ");
        } 
        System.out.println();
    }

    
    public static void main(String[] args) {
        RmDuplicates rd = new RmDuplicates();
        int[] nums = new int[]{1, 1, 1, 2, 2, 5, 3, 3};
        print(nums, nums.length);
        print(nums, rd.removeDuplicates(nums));

    }


}
