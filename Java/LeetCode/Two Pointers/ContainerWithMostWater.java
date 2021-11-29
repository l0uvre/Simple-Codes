/*** LC 11 --- Array, Two Pointers ***/
public class ContainerWithMostWater {

    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1;
        int res = -1;
        while (i < j) {
            int volume = 0;
            if (height[i] < height[j]) {
                volume = height[i] * (j - i); 
                i++;
            } else {
                volume = height[j] * (j - i);
                j--;
            }
            res = Math.max(res, volume);
        }
        return res;
    }

    public static void main(String[] args) {
        ContainerWithMostWater sol = new ContainerWithMostWater();  
        int[] height = new int[]{1,8,6,2,5,4,8,3,7};
        System.out.println(sol.maxArea(height));

        height = new int[]{1, 1};
        System.out.println(sol.maxArea(height));

        height = new int[]{4, 3, 2, 1, 4};
        System.out.println(sol.maxArea(height));

        height = new int[]{1, 2, 1};
        System.out.println(sol.maxArea(height));
    }
}
