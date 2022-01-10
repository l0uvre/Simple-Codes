/** LC 1868 -- Two Pointers. **/
import java.util.*;
public class ProductRLEArrays {
    /** use two pointers to point at the segments of encoded arrays;
     * every time, we create a product segment with the length of the shorter
     * segment, increment the pointer of the shorter segments and 
     * decrease the length of the longer segments;
     * then add the product segment to the result . **/
    public List<List<Integer>> findRLEArray(int[][] encoded1, int[][] encoded2) {
        List<List<Integer>> res = new ArrayList<>();
        int m = encoded1.length;
        int n = encoded2.length;
        int i = 0;
        int j = 0;
        while (i < m && j < n) {
            int[] segment1 = encoded1[i];
            int[] segment2 = encoded2[j];
            int len = Math.min(segment1[1], segment2[1]);

            /** move the pointers accordingly. **/
            if (segment1[1] == len && segment2[1] == len) {
                i++;
                j++;
            } else if (segment1[1] == len) {
                i++;
                segment2[1] -= len;
            } else if (segment2[1] == len) {
                j++;
                segment1[1] -= len;
            }

            int product = segment1[0] * segment2[0];
            /** if the product segment can be concatenated. **/
            if (!res.isEmpty()) {
                List<Integer> lastSeg = res.get(res.size() - 1);
                if (lastSeg.get(0) == product) {
                    lastSeg.set(1, lastSeg.get(1) + len);
                    continue;
                }
            }
            
            /** otherwise, add a new product segment to the result.**/
            List<Integer> newSeg = new ArrayList<>();
            newSeg.add(product);
            newSeg.add(len);
            res.add(newSeg);
        }
        return res;
    }

    public static void main(String[] args) {
        ProductRLEArrays sol = new ProductRLEArrays();
        int[][] encoded1 = new int[][]{
            {1,3}, {2,3}
        };
        int[][] encoded2 = new int[][]{
            {6,3}, {3,3}
        };
        System.out.println(sol.findRLEArray(encoded1, encoded2));

        encoded1 = new int[][]{
            {1,3}, {2,1}, {3,2}
        };

        encoded2 = new int[][]{
            {2,3}, {3,3}
        };
        System.out.println(sol.findRLEArray(encoded1, encoded2));


    }
}
