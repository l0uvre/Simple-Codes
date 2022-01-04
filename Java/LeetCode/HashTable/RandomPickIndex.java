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
}
