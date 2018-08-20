class HouseRobberII {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        return Math.max(rob(nums, 0, nums.length - 2), rob(nums, 1, nums.length - 1));
    }
    
    private int rob(int[] nums, int start, int end) {
        if (start == end) {
            return nums[start];
        } else if (end - start == 1) {
            return Math.max(nums[start], nums[end]);
        }
        //int[] dp = new int[end - start + 1];
        int[] dp = new int[3]; // only need three number for space
        dp[0] = nums[start];
        dp[1] = Math.max(dp[0], nums[start + 1]);
        for (int i = 2; i < end - start + 1; i++) {
            //dp[i] = Math.max(dp[i - 2] + nums[i + start], dp[i - 1]);
            dp[2] = Math.max(dp[0] + nums[i + start], dp[1]);
            dp[0] = dp[1];
            dp[1] = dp[2];
        }
        return dp[2];
    }

    public static void main(String[] args) {
        HouseRobberII sol = new HouseRobberII();
        System.out.println(sol.rob(new int[]{2,3,2}));
    }
}
