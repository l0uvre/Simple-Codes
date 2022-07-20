/** LC 1029 -- Sorting, Greedy. **/
import java.util.Arrays;

public class TwoCityScheduling {
    /** we sort the costs array based on the diff of
     * costs[0] - costs[1] in an ascending order, then we
     * know we can fly the first N people to city A; assume
     * we fly all 2n people to city B, then we pay costs[i][0] 
     * - costs[i][1] for each among the n people flying to A;
     *   the smaller, the better. **/
    public int twoCitySchedCost(int[][] costs) {
        Arrays.sort(costs, (a, b) -> Integer.compare(a[0] - a[1],
                    b[0] - b[1]));
        int n = costs.length / 2;
        int res = 0;
        for (int i = 0; i < n; i++) {
            res += costs[i][0];
        }
        for (int i = n; i < costs.length; i++) {
            res += costs[i][1];
        }
        return res;
    }

    public static void main(String[] args) {
        TwoCityScheduling sol = new TwoCityScheduling();
        int[][] costs = new int[][]{
            {10,20},{30,200},{400,50},{30,20}
        };
        System.out.println(sol.twoCitySchedCost(costs));

        costs = new int[][]{
            {259,770},{448,54}, {926,667}, {184,139}, 
                {840,118}, {577,469}
        };
        System.out.println(sol.twoCitySchedCost(costs));
    }
}
