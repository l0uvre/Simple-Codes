/** LC 75 - Array, Counting Sort. **/
import java.util.Arrays;
public class SortColors {
    /** use counting sort to count the occurences of 
     * 0s, 1s, 2s with one pass;
     * and then modify the input array with the counting array. **/
    public void sortColors(int[] nums) {
        int[] counts = new int[3];
        for (int num : nums) {
            counts[num] += 1;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < counts.length; j++) {
                if (counts[j] > 0) {
                    nums[i] = j;
                    counts[j] -= 1;
                    break;
                }
            }
        }
        return;
    }

    public static void main(String[] args) {
        SortColors sol = new SortColors();
        int[] nums = new int[] {2, 1, 1, 2, 2, 0, 1, 1}; 
        sol.sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}
