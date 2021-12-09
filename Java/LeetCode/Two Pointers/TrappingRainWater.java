/** LC 42 --- Two Pointers. ***/
public class TrappingRainWater {


    /** a solution I came up with: loop through the array
     * and find the index of the highest bar;
     * do one half pass from left to the index of the highest, 
     * update the value of highest bar to the
     * left (leftHighest) of the current bar, 
     * if the current is lower, we add (leftHighest - curr)
     * to the result;
     * Similary do another pass from right to the highest;
     * O(N). **/
    public int trap(int[] height) {
        int amount = 0;
        int highestIndex = -1;
        int highest = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[i] > highest) {
                highest = height[i];
                highestIndex = i;
            }
        }
        
        int highestLeft = 0;
        for (int i = 0; i < highestIndex; i++) {
            if (height[i] >= highestLeft) {
                highestLeft = height[i];
            } else {
                amount += highestLeft - height[i];
            }
        }

        int highestRight = 0;
        for (int i = height.length - 1; i > highestIndex; i--) {
            if (height[i] < highestRight) {
                amount += highestRight - height[i];
            } else {
                highestRight = height[i];
            }
        }
        return amount;
    }

    public static void main(String[] args) {
        int[] height = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        TrappingRainWater sol = new TrappingRainWater(); 
        System.out.println(sol.trap(height));

        height = new int[]{4,2,0,3,2,5};
        System.out.println(sol.trap(height));
    }
}
