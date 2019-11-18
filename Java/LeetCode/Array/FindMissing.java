//https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
import java.util.*;

public class FindMissing {

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int num : nums) {
            int i = Math.abs(num) - 1;
            nums[i] = - Math.abs(nums[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                res.add(i + 1); 
            }
        }
        return res;
    }
    
    public static void main(String[] args) {
        FindMissing fm = new FindMissing();
        int[] nums = new int[] {4,3,2,7,8,2,3,1};
        System.out.println(fm.findDisappearedNumbers(nums));
    }
}
