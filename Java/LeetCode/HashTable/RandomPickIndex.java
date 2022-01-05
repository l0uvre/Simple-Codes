/** LC 398 -- HashTable, Randomized. **/
import java.util.*;
public class RandomPickIndex {

    Map<Integer, List<Integer>> map;
    public RandomPickIndex(int[] nums) {
        map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.putIfAbsent(nums[i], new ArrayList<>());
            map.get(nums[i]).add(i);
        }
    }
    
    public int pick(int target) {
        List<Integer> indices = map.get(target);
        int index = indices.get((int) (Math.random() * indices.size()));
        return index;
    }

    /** Reservoir Sampling. **/
    int[] nums;
    Random random;
    public RandomPickIndex(int[] nums) {
        this.nums = nums;
        random = new Random();
    }

    public int pick(int target) {
        int count = 0;
        int index = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                count += 1;
                if (random.nextInt(count) == 0) {
                    index = i;
                }
            }
        }
        return index;
    }
}
