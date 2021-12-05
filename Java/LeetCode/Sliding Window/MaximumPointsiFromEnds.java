/** LC 1423 -- Dynamic Programming / Sliding Window and Prefix Sum 
 * It was a Google Phone Interview Question.
 * My inital sol was a recursion + memoization and it cost a lot of memory.
 * ***/
import java.util.*;
public class MaximumPointsiFromEnds {
    /** Dynamic Programming Approach 
     * Essentially Sliding Window***/
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        /** dp[i] means take the first i cards from the beginning
         * and k - i cards from the end **/
        int[] dp = new int[k + 1];
        for (int i = 0; i < k; i++) {
            dp[0] += cardPoints[n - 1 - i];
        }
        
        int res = dp[0];
        for (int i = 1; i < dp.length; i++) {
            /** relate the subproblems' solution***/
            dp[i] = dp[i - 1] + cardPoints[i - 1] -
                cardPoints[n - k + i - 1];
            res = Math.max(res, dp[i]);
        }
        return res;
    } 

    public static void main(String[] args) {
        MaximumPointsiFromEnds sol = new MaximumPointsiFromEnds();  
        int[] cardPoints = new int[]{1,2,3,4,5,6,1};
        int k = 3;
        System.out.println(sol.maxScore(cardPoints, k));
    }
}
