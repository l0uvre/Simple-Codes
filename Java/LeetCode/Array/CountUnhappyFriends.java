/** LC 1583 -- Array, Simulation. **/
import java.util.*;
public class CountUnhappyFriends {
    /** use a matrix int[][] rank to save how person i likes person j,
     * the smaller value, the greater extent;
     * 
     * then we do a O(len(pairs)^2) loop to check if there's any unhappy friends. **/
    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        int[][] rank = new int[n][n];
        for (int i = 0; i < preferences.length; i++) {
            for (int j = 0; j < preferences[i].length; j++) {
                rank[i][preferences[i][j]] = j;
            }
        }

        Set<Integer> res = new HashSet<>();
        for (int i = 0; i < pairs.length; i++) {
            for (int j = i + 1; j < pairs.length; j++) {
                int first = pairs[i][0];
                int second = pairs[i][1];
                int third = pairs[j][0];
                int forth = pairs[j][1];
                if (rank[first][second] > rank[first][third] &&
                        rank[third][forth] > rank[third][first]) {
                    res.add(first);
                    res.add(third);
                        }

                if (rank[first][second] > rank[first][forth] &&
                        rank[forth][third] > rank[forth][first]) {
                    res.add(first);
                    res.add(forth);
                        }

                if (rank[second][first] > rank[second][third] &&
                        rank[third][forth] > rank[third][second]) {
                    res.add(second);
                    res.add(third);
                        }
                if (rank[second][first] > rank[second][forth] &&
                        rank[forth][third] > rank[forth][second]) {
                    res.add(second);
                    res.add(forth);
                        }
            }
        }
        return res.size();
    }

    public static void main(String[] args) {
        CountUnhappyFriends sol = new CountUnhappyFriends();
        int n = 4;
        int[][] preferences = new int[][]{
            {1, 2, 3},
                {3, 2, 0}, 
                {3, 1, 0}, 
                {1, 2, 0}
        };

        int[][] pairs = new int[][]{
            {0, 1}, 
                {2, 3}
        };
        System.out.println(sol.unhappyFriends(n, preferences, pairs));


        preferences = new int[][]{
            {1, 3, 2}, 
                {2, 3, 0}, 
                {1, 3, 0}, 
                {0, 2, 1}
        };
        pairs = new int[][]{
            {1, 3},
                {0, 2}
        };
        n = 4;
        System.out.println(sol.unhappyFriends(n, preferences, pairs));
    }
}
