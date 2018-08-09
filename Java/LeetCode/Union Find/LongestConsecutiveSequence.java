import java.util.*;

public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        UnionFind uf = new UnionFind(n);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (map.containsKey(nums[i])) {
                continue;
            }  // jump duplicate elements.
            
            map.put(nums[i], i);
            if (map.containsKey(nums[i] - 1)) {
                uf.union(map.get(nums[i] - 1), i);
            }
            if (map.containsKey(nums[i] + 1)) {
                uf.union(map.get(nums[i] + 1), i);
            }
        }
        
        return uf.maxUnionSize();
    }

    public static void main(String[] args) {
      LongestConsecutiveSequence sol = new LongestConsecutiveSequence();
      int[] nums = {0,1,4,3,6,2,7,9,8,11,10};
      System.out.println(sol.longestConsecutive(nums));
    }
}

class UnionFind {
    int[] id;
    int[] size;
    
    public UnionFind(int n) {
        id = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            size[i] = 1;
        }
    }
    
    public int find(int i) {
        while (id[i] != i) {
            i = id[i];
        }
        return i;
    }
    
    public void union(int i, int j) {
        int id1 = find(i);
        int id2 = find(j);
        
        if (size[id1] > size[id2]) {
            id[id2] = id1;
            size[id1] += size[id2];
        } else {
            id[id1] = id2;
            size[id2] += size[id1];
        }
    }
    
    public int maxUnionSize() {
        int res = 0;
        for (int i = 0; i < size.length; i++) {
            res = Math.max(size[i], res);
        }
        
        return res;
    }
    
}
