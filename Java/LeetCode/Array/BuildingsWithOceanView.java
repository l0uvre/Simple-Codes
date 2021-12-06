/*** LC 1762 --- Array / Monotonic Stack ***/
import java.util.*;
public class BuildingsWithOceanView {
    /**maintain a monotonic stack to use max value
     * on the top, traversing from right to left
     * O(n)**/
    public int[] findBuildingsStack(int[] heights) {
        /** use stack to store the indices of the buildings
         * with an ocean view **/
        Deque<Integer> stack = new LinkedList<>();
        for (int i = heights.length - 1; i >= 0; i--) {
            if (stack.isEmpty()) {
                stack.push(i);
            } else {
                if (heights[i] > heights[stack.peek()]) {
                    stack.push(i);
                } 
            }
        }
        int[] res = new int[stack.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = stack.pop();
        }
        return res;
    }

    /** Simple traversing approach **/
    public int[] findBuildings(int[] heights) {
        int highestRight = 0;
        List<Integer> validIndices = new ArrayList<>();
        for (int i = heights.length - 1; i >= 0; i--) {
            if (heights[i] > highestRight) {
                validIndices.add(i);
                highestRight = heights[i];
            }
        }
        int[] res = new int[validIndices.size()]; 
        for (int i = 0; i < res.length; i++) {
            res[i] = validIndices.get(res.length - 1 - i);
        }
        return res;
    }

    public static void main(String[] args) {
        BuildingsWithOceanView sol = new BuildingsWithOceanView(); 
        int[] heights = new int[]{4,2,3,1};
        System.out.println(Arrays.toString(sol.findBuildings(heights)));

        heights = new int[]{4,3,2,1};
        System.out.println(Arrays.toString(sol.findBuildings(heights)));

        heights = new int[]{1,3,2,4};
        System.out.println(Arrays.toString(sol.findBuildings(heights)));
    }
}
