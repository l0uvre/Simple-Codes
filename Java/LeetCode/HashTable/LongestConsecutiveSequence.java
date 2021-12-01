/*** LC 128 --- HashTable, Array ***/
import java.util.*;

public class LongestConsecutiveSequence {

    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        } else {
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                set.add(num);
            }
            int res = 0;
            for (int num : nums) {
                /** it's the start point of a sequence **/
                if (!set.contains(num - 1)) {
                    int end = num + 1;
                    while (set.contains(end)) {
                        end += 1;
                    }
                    res = Math.max(end - num, res);
                }
            }
            return res;
        }
    }

    public static void main(String[] args) {
        LongestConsecutiveSequence sol = new LongestConsecutiveSequence(); 
        int[] nums = new int[]{100,4,200,1,3,2};
        System.out.println(sol.longestConsecutive(nums));

        nums = new int[]{0,3,7,2,5,8,4,6,0,1};
        System.out.println(sol.longestConsecutive(nums));

    }
}
