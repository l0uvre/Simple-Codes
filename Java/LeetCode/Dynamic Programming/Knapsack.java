
import java.util.Scanner;

/***
 * 3 8
 * 7 6
 * 5 4
 * 3 3
 */
public class Knapsack {

  public int knapsack(int capacity, int[] costs, int[] vals) {
    int m = costs.length; // number of items item[0: m[
    int n = capacity; // capacity.
    int[][] dp = new int[m + 1][n + 1];
    // subproblems, items[i:], capaticity[j:]
    dp[m][n] = 0;
    for (int i = m - 1; i >=0; i--) {
      for (int j = n; j >= 0; j--) {
        if (j >= costs[i]) {
          dp[i][j] = Math.max(dp[i + 1][j], dp[i + 1][j - costs[i]] + vals[i]); // guess: put the item in or not.
        } else {
          dp[i][j] = dp[i + 1][j];
        }
      }
    }
    return dp[0][n]; // original problem : item[0:] with capacity N.
  }

  public static void main(String[] args) {
    /***
     * 3 8  m: number of items; n: total capacity
     * 7 6  a b : cost value
     * 5 4
     * 3 3
     */
    Scanner in = new Scanner(System.in);
    int m = in.nextInt();
    int n = in.nextInt();

    int[] costs = new int[m];
    int[] vals = new int[m];
    for(int i = 0; i < m; i++) {
      int c = in.nextInt();
      int d = in.nextInt();
      costs[i] = c;
      vals[i] = d;
    }

    int result;
    Knapsack sol = new Knapsack();
    result = sol.knapsack(n, costs, vals);
    System.out.println(result);
  }
}
