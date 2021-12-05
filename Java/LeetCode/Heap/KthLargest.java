/** LC 215 --- Heap / Quick Select ***/
import java.util.*;
public class KthLargest {

    /** Quick select, use partition to get the kth element **/
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int j = partition(nums, left, right);
            /** it's Kth largest, so the index 
             * should be n - k **/
            if (j < n - k) {
                left = j + 1;
            } else if (j > n - k) {
                right = j - 1;
            } else {
                break;
            }
        }
        return nums[n - k];
    }

    private int partition(int[] nums, int left, int right) {
        int pivot = nums[left];
        int i = left;
        int j = right + 1;
        while (true) {
            do {
                i++;
            } while (i <= right && nums[i] < pivot);

            do {
                j--;
            } while (nums[j] > pivot);

            if (i >= j) {
                break;
            }
            swap(nums, i, j);
        }
        swap(nums, left, j);
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /** Use a min heap of size k to store top k elements **/
    public int findKthLargestPQ(int[] nums, int k) {
        Queue<Integer> minHeap = new PriorityQueue<>((a, b)
                -> Integer.compare(a, b));
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }

    public static void main(String[] args) {
        KthLargest sol = new KthLargest(); 
        int[] nums = new int[]{3,2,1,5,6,4};
        int k = 2;
        System.out.println(sol.findKthLargest(nums, k));


        nums = new int[]{3,2,3,1,2,4,5,5,6};
        k = 4;
        System.out.println(sol.findKthLargest(nums, k));


    }
}
