// LC540 --- Array, Binary Search
public class SingleEleInSorted {

    public int singleNonDuplicate(int[] nums) {
        int left = 0, right = nums.length - 1;    
        int single = nums[0];
        while (left < right) {
            int mid = (right - left) / 2 + left;
            // in the sorted part, the index of the first one in the pair is even. 0,0,1,1
            if (mid % 2 == 1) {
                mid--;
            }
            // check whether it's a pair
            if (nums[mid] != nums[mid + 1]) {
                // if not then the single one must be in the left
                right = mid;
            } else {
                left = mid + 2;
            }
        }

        return nums[left];
    }

    public static void main(String[] args) {
        SingleEleInSorted sol = new SingleEleInSorted();
        int[] nums = new int[] {1};
        System.out.println(sol.singleNonDuplicate(nums));

        nums = new int[]{1,1,2,3,3,4,4,8,8};
        System.out.println(sol.singleNonDuplicate(nums));

        nums = new int[]{1,1,2,2,3};
        System.out.println(sol.singleNonDuplicate(nums));

        nums = new int[]{3,3,7,7,10,11,11};
        System.out.println(sol.singleNonDuplicate(nums));
    }

}
