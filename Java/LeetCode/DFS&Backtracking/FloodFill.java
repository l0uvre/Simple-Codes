class FloodFill {
    private static int[] rows = {1, -1, 0, 0};
    private static int[] cols = {0, 0, 1, -1};
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image[sr][sc] == newColor) {
            return image;
        }
        dfs(image, sr, sc, image[sr][sc], newColor);
        return image;
    }
    
    private void dfs(int[][] image, int sr, int sc, int oldColor, int newColor) {
        image[sr][sc] = newColor;
        for (int i = 0; i < 4; i++) {
            int nsr = sr + rows[i];
            int nsc = sc + cols[i];
            if (nsr >= 0 && nsr < image.length 
                && nsc >= 0 && nsc < image[nsr].length) {
                if (image[nsr][nsc] == oldColor) {
                    dfs(image, nsr, nsc, oldColor, newColor);
                }
            }
        }
    }
    
    public static void show(int[][] image) {
        for (int[] row : image) {
            for (int i : row) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[][] image = {{1,1,1}, {1,1,0}, {1,0,1}};
        show(image);
        FloodFill sol = new FloodFill();
        sol.floodFill(image, 1, 1, 2);
        show(image);
    }

}
