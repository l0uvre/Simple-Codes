import java.util.*;
// LC 373  Heap
public class KPairSmallestSum {

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new LinkedList<>();
        Queue<List<Integer>> pq = new PriorityQueue<>(k, (a, b) -> Integer.compare(a.get(0) + a.get(1),
                    b.get(0) + b.get(1)));
        if (nums1 == null || nums2 == null || nums1.length * nums2.length == 0) {
            return res; 
        }  

        for (int i = 0; i < Math.min(nums1.length, k); i++) {
            pq.offer(Arrays.asList(nums1[i], nums2[0], 0));              
        }

        for (int i = 0; i < Math.min(k, nums1.length * nums2.length); i++) { 
            List<Integer> curr = pq.poll();
            res.add(Arrays.asList(curr.get(0), curr.get(1)));
            if (curr.get(2) < nums2.length - 1) {
                pq.offer(Arrays.asList(curr.get(0), nums2[curr.get(2) + 1], curr.get(2) + 1));
            }
        }
        
        return res;
    }

    public static void main(String[] args) {
        KPairSmallestSum kpss = new KPairSmallestSum();
        int[] nums1 = new int[]{1, 7, 11};
        int[] nums2 = new int[]{2, 4, 6};
        int k = 3;
        System.out.println(kpss.kSmallestPairs(nums1, nums2, k));
        nums1 = new int[]{1, 1, 2};
        nums2 = new int[]{1, 2, 3};
        k = 2;
        System.out.println(kpss.kSmallestPairs(nums1, nums2, k));

    }

}
