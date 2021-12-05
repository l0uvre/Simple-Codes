// Leetcode 347 Heap
import java.util.*;

public class TopKFreq {

    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length < k) {
            return res; 
        } else {
            Map<Integer, Integer> freq = new HashMap<>();
            List<Integer>[] freqToInts = new List[nums.length + 1];
            for (int num : nums) {
                freq.put(num, freq.getOrDefault(num, 0) + 1); 
            }
            for (Integer num : freq.keySet()) {
                Integer numFreq = freq.get(num);
                if (freqToInts[numFreq] == null) {
                    freqToInts[numFreq] = new LinkedList<>();
                }
                freqToInts[numFreq].add(num);
            }

            for (int i = freqToInts.length - 1; i >= 0 && res.size() < k; i--) {
                if (freqToInts[i] != null) {
                    res.addAll(freqToInts[i]);
                }
            }
            return res;
        }
    }

    /** use a minHeap to store at most top K elements
     * O(nlogk)
     * **/
    public int[] topKFrequent(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return new int[]{}; 
        } else {
            Map<Integer, Integer> freq = new HashMap<>();
            Queue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(
                    (a, b) -> Integer.compare(a.getValue(), b.getValue()));
            for (int num : nums) {
                freq.put(num, freq.getOrDefault(num, 0) + 1); 
            }

            for (Map.Entry<Integer, Integer> entry: freq.entrySet()) {
                minHeap.offer(entry); 
                if (minHeap.size() > k) {
                    minHeap.poll();
                }
            }
            int[] res = new int[k];
            for (int i = k - 1; i >= 0; i--) {
                res[i] = minHeap.poll().getKey();
            } 
            return res;
        }
    }

    //Method II: using a priority queue
    public List<Integer> topKFrequentII(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length < k) {
            return res; 
        } else {
            Map<Integer, Integer> freq = new HashMap<>();
            Queue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(
                    (a, b) -> Integer.compare(a.getValue(), b.getValue()));
            for (int num : nums) {
                freq.put(num, freq.getOrDefault(num, 0) + 1); 
            }

            for (Map.Entry<Integer, Integer> entry: freq.entrySet()) {
                minHeap.offer(entry); 
                if (minHeap.size() > k) {
                    minHeap.poll();
                }
            }

            while (!minHeap.isEmpty()) {
                res.add(minHeap.poll().getKey());
            }
            return res;
        }
    }

    public static void main(String[] args) {
        TopKFreq tkf = new TopKFreq();
        int[] nums = new int[]{1,1,1,2,2,3};
        int k = 2;
        System.out.println(tkf.topKFrequent(nums, k));
        System.out.println(tkf.topKFrequentII(nums, k));
    }

}
