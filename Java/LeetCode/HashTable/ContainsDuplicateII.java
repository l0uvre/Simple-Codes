import java.util.*;
class ContainsDuplicateII {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                if (Math.abs(map.get(nums[i]) - i) <= k) {
                    return true;
                }
            } else {
                map.put(nums[i], i);
            }
            map.put(nums[i], i);
        }
        return false;
    }
    public static void main(String[] args) {
        ContainsDuplicateII sol = new ContainsDuplicateII();
        System.out.println(sol.containsNearbyDuplicate(new int[]{1,0,1,1}, 1));
        System.out.println(sol.containsNearbyDuplicate(new int[]{1,2,3,1,2,3}, 2));
    }
}
