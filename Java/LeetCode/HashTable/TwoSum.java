/** LC 1 - Array, HashTable. **/
public class TwoSum {

    /** use a hashmap to keep track of nums and their indices,
     * iterate the array, for a specific item, if we have (target - num) in
     * our hashmap, then we get the result. **/
    public int[] twoSum(int[] nums, int target) {
        /** <num, index>. **/
        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                res[0] = map.get(target - nums[i]);
                res[1] = i;
                return res;
            }
            map.put(nums[i], i);
        }
        return res;
    }
}
