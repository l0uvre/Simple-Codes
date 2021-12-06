/*** LC 1570 --- Hashtable  ***/
import java.util.*;
public class SparseVector {
    /** use a hashmap to store the <index, value>
     * pair for non zero values **/
    private Map<Integer, Integer> map;
    
    SparseVector(int[] nums) {
        map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                map.put(i, nums[i]);
            }
        }
    }
    
	// Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        int dotProduct = 0;
        /*** we can iterate the map of smaller size **/
        for (int index : vec.map.keySet()) {
            if (this.map.containsKey(index)) {
                dotProduct += vec.map.get(index) *
                    this.map.get(index);
            }
        }
        return dotProduct;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1,0,0,2,3};
        int[] nums2 = new int[]{0,3,0,4,0};
        SparseVector sv1 = new SparseVector(nums1);
        SparseVector sv2 = new SparseVector(nums2);
        System.out.println(sv1.dotProduct(sv2));


        nums1 = new int[]{0,1,0,0,2,0,0};
        nums2 = new int[]{1,0,0,0,3,0,4};
        sv1 = new SparseVector(nums1);
        sv2 = new SparseVector(nums2);
        System.out.println(sv1.dotProduct(sv2));

        nums1 = new int[]{0,1,0,0,0};
        nums2 = new int[]{0,0,0,0,2};
        sv1 = new SparseVector(nums1);
        sv2 = new SparseVector(nums2);
        System.out.println(sv1.dotProduct(sv2));

    }

}
