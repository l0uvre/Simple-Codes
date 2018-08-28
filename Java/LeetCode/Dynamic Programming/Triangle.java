import java.util.*;

class Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle == null || triangle.size() == 0){
        	return 0;
        }
        int n = triangle.size();
        int[][] dp = new int[n][n];
        
        for (int i = 0; i < triangle.get(n - 1).size(); i++) {
            dp[n - 1][i] = triangle.get(n - 1).get(i);
        }
        
        for(int i = n - 2; i >= 0; i--) {
        	for(int j = 0; j < triangle.get(i).size(); j++) {
        		dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
        	}
        }
        
        return dp[0][0];
    }

    public static void main(String[] args) {
        Triangle sol = new Triangle();
        List<List<Integer>> triangle = new LinkedList<>();
        triangle.add(Arrays.asList(2));
        triangle.add(Arrays.asList(3, 4));
        triangle.add(Arrays.asList(6, 5, 7));
        triangle.add(Arrays.asList(4, 1, 8, 3));
        System.out.println(sol.minimumTotal(triangle));
    }
}
