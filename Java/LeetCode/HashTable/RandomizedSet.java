/** LC 380 -- HashTable. **/
import java.util.*;
public class RandomizedSet {
 
    /** use a hashmap and an arraylist;
     * hashmap for track of the indices of the elements
     * in the arraylist; arraylist for the random access;
     * when you try to remove an element, update the index of
     * that element with the last element in the arraylist and the hashmap too. **/
    Map<Integer, Integer> indices;
    List<Integer> nums;
    Random rnd;
    public RandomizedSet() {
        rnd = new Random();
        indices = new HashMap<>();
        nums = new ArrayList<>();
    }
    
    public boolean insert(int val) {
        if (indices.containsKey(val)) {
            return false;
        } else {
            indices.put(val, nums.size());
            nums.add(val);
            return true;
        }
    }
    
    public boolean remove(int val) {
        if (!indices.containsKey(val)) {
            return false;
        } else {
            int index = indices.get(val);
            nums.set(index, nums.get(nums.size() - 1));
            indices.put(nums.get(nums.size() - 1), index);

            nums.remove(nums.size() - 1);
            indices.remove(val);
            return true;
        }
    }
    
    public int getRandom() {
        int index = rnd.nextInt(nums.size());
        return nums.get(index);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
