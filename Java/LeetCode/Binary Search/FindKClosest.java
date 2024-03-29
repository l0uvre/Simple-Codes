/** LC 658 --- Binary Search. **/
import java.util.*;

public class FindKClosest {

    /** Use Binary Search since the input arr is sorted;
     * View the points in the same line so the k closest points to
     * x must be contigous;
     *
     * Do binary search on the starting index of the k points;
     * The range is [0, n - k];
     * if (x - starting point) > (ending point + 1 - x),
     * then all left starting point to it shouldn't be considered;
     * else, then all the right starting point to it should be eliminated.
     * **/
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();
        int left = 0;
        /** the rightmost starting index for the k points is n - k **/
        int right = arr.length - k;
        while (left < right) {
            int mid = left + (right - left) / 2;
            /** arr[mid] ...x... arr[mid + k - 1], arr[mid + k]  **/
            if ((x - arr[mid]) > (arr[mid + k] - x)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        for (int i = left; i < left + k; i++) {
            res.add(arr[i]);
        }
        return res;
    }

    /** use a max heap to keep the k closest elements which didn't
     * account for the sorting order of the input arr. **/
    public List<Integer> findClosestElementsNaive(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();
        if (arr == null || arr.length == 0) {
            return res;
        }
        // Maximum heap at most K 
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> { 
          if (Math.abs(a - x) == Math.abs(b - x)) {
              /** break the tie by preferring the smaller element. **/
            return Integer.compare(b, a); 
          } else {
              return Integer.compare(Math.abs(b - x), Math.abs(a - x));
          }
        });

        for (int ele : arr) { // n arr.length
            pq.offer(ele);   //O(logk)
            if (pq.size() > k) {
                pq.poll();
            }   
        }

        while (!pq.isEmpty()) {
            res.add(pq.poll());  // (7, 4, 5)
        }  //O(klogk)
        Collections.sort(res); // (4, 5, 7) // O(klogk)
        return res;  //O(nlogk)  // O(k)
    }
}
