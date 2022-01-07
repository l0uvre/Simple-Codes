/** LC 1011 -- Binary Search, Greedy. **/
import java.util.*;
public class CapacityShipPackages {
    /** Do a binary search on the [max(weights), sum(weights)];
     * */
    public int shipWithinDays(int[] weights, int days) {
        int left = Arrays.stream(weights).max().getAsInt();
        int right = Arrays.stream(weights).sum();
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canBeShipped(mid, weights, days)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean canBeShipped(int capacity, int[] weights, int days) {
        int count = 0;
        int sumOfWeight = 0;
        for (int weight : weights) {
            if (weight > capacity) {
                return false;
            } 
            if (sumOfWeight + weight <= capacity) {
                sumOfWeight += weight;
            } else {
                sumOfWeight = weight;
                count += 1;
            }
        }
        /** the last day for the remaining packages. **/
        count += 1;
        return count <= days;
    }

    public static void main(String[] args) {
        CapacityShipPackages sol = new CapacityShipPackages();  
        int[] weights = new int[]{1,2,3,4,5,6,7,8,9,10};
        int days = 5;
        System.out.println(sol.shipWithinDays(weights, days));

        weights = new int[]{3,2,2,4,1,4};
        days = 3;
        System.out.println(sol.shipWithinDays(weights, days));

        weights = new int[]{1,2,3,1,1};
        days = 4;
        System.out.println(sol.shipWithinDays(weights, days));

    }

}
