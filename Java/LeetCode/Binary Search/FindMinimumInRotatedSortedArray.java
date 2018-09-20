class FindMinimumInRotatedSortedArray {
    public int findMin(int[] nums) {
        
        if (nums.length == 1) {
            return nums[0];
        }
        int begin = 0;
        int end = nums.length - 1;
        int mid = -1;
        
        while (begin <= end) {
            if (nums[begin] < nums[end]) {
                return nums[begin];
            }
            
            mid = (end - begin) / 2 + begin;
            if (nums[mid] > nums[mid + 1]) {
                return nums[mid + 1];
            }
            
            if (nums[mid - 1] > nums[mid]) {
                return nums[mid];
            }
            
            if (nums[begin] < nums[mid]) {
                begin = mid + 1; 
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }
    
    public static void main(String[] args) {
        FindMinimumInRotatedSortedArray sol = new FindMinimumInRotatedSortedArray();
        System.out.println(sol.findMin(new int[]{1, 2}));
        System.out.println(sol.findMin(new int[]{2, 1}));
        System.out.println(sol.findMin(new int[]{5, 1, 2, 3, 4}));
        System.out.println(sol.findMin(new int[]{1}));

    }
}
