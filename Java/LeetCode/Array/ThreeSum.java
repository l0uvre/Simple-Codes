import java.util.*;

public class ThreeSum {
    
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                int sum = nums[l] + nums[r] + nums[i];
                if (sum > 0) {
                    r--;
                } else if (sum < 0) {
                    l++;
                } else {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while (l < r && nums[l] == nums[l + 1]) {
                        l++;
                    }
                    while (l < r && nums[r] == nums[r - 1]) {
                        r--;
                    }
                    l++;
                    r--;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        ThreeSum ts = new ThreeSum();
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        System.out.println(ts.threeSum(nums));

        nums = new int[]{-2, 0, 1, 1, 2};
        System.out.println(ts.threeSum(nums));

        nums = new int[]{-2 ,-1, -1, 0, 1, 1, 2};
        System.out.println(ts.threeSum(nums));
    }
}
