/** LC 1891 --- Binary Search. **/
import java.util.*;
public class CuttingRibbons {


    /** the largest possible length of equal ribbons is max(ribbons),
     * we can do a binary search on [0, max(ribbions)]; 
     * use (left + right + 1) / 2 to prefer the right one when
     * left = right - 1.  **/
    public int maxLength(int[] ribbons, int k) {

        int left = 0;
        int right = Arrays.stream(ribbons).max().getAsInt();

        while (left < right) {
            /** use +1 on the top of the fraction to prefer the right.**/
            int mid = left + (right - left + 1) / 2;
            if (canCutIntoK(mid, ribbons, k)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private boolean canCutIntoK(int equalLen, int[] ribbons, int k) {
        int count = 0;
        for (int i : ribbons) {
            count += i / equalLen;
        }
        return count >= k;
    }

    public static void main(String[] args) {
        CuttingRibbons sol = new CuttingRibbons(); 
        int[] ribbons = new int[]{9, 7, 5};
        int k = 3;
        System.out.println(sol.maxLength(ribbons, k));

        ribbons = new int[]{7,5,9};
        k = 4;
        System.out.println(sol.maxLength(ribbons, k));

        ribbons = new int[]{5,7,9};
        k = 22;
        System.out.println(sol.maxLength(ribbons, k));
    }
}
