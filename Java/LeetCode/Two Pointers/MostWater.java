//Two Pointers. Leetcode 11
//https://leetcode.com/problems/container-with-most-water/
public class MostWater {

    public int maxArea(int[] height) {
        int res = 0;
        if (height == null || height.length < 2) {
            return res;
        } else {
            int left = 0, right = height.length - 1;
            while (left < right) {
                int h1 = height[left];
                int h2 = height[right];
                res = Math.max(res, Math.min(h1, h2) * (right - left));
                // System.out.println(left + "    " + right + "     " + res);
                if (height[left] < height[right]) {
                    left++;
                } else {
                    right--;
                }   
               
            }
            return res;
        }
    }

    public static void main(String[] args) {
        int[] height = new int[]{2,3,4,5,18,17,6};
        MostWater mw = new MostWater();
        System.out.println(mw.maxArea(height));

        height = new int[]{1,8,6,2,5,4,8,3,7};
        System.out.println(mw.maxArea(height));
    }
}
