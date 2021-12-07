/*** LC 528 -- Prefix Sum, Binary Search, Array --- **/
import java.util.*;
public class RandomPick {
    int[] prefixSum;
    Random random;

    /** We calculate the prefix sum array,
     * [2, 4, 1, 6, 5] -> [2, 6, 7, 13, 18]
     * then generate a random integer [1:18]
     *
     * [1:2] -> return 0
     * [3:6] -> return 1 etc **/
    public RandomPick(int[] w) {
        random = new Random();
        prefixSum = new int[w.length];
        prefixSum[0] = w[0];
        for (int i = 1; i < w.length; i++) {
            prefixSum[i] = w[i] + prefixSum[i - 1];
        }
    }
    
    public int pickIndex() {
        int left = 0;
        int right = prefixSum.length - 1;
        int rWeight = 1 + random.nextInt(prefixSum[right]);

        /** do binary search to find the index of the value which
         * the random weight we generated is just smaller than**/
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (prefixSum[mid] == rWeight) {
                return mid;
            } else if (prefixSum[mid] > rWeight) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        RandomPick solution = new RandomPick(new int[]{1, 3});

        System.out.println(solution.pickIndex()); 
        System.out.println(solution.pickIndex()); 
        System.out.println(solution.pickIndex()); 
        System.out.println(solution.pickIndex()); 
        System.out.println(solution.pickIndex()); 
    }
}
