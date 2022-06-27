/** LC 465 -- Array, Backtracking. */

import java.util.*;
public class OptimalAccBalancing {

    /** use debts[i] = num to indicate how many balance person i holds;
    num > 0 means that person needs to give some money out;
    num == 0, just skip;
    num < 0 means that person should receive some money;
    do the backtracking on the debts array and update the min times of trans
    needed in the process. */
    int minTransac;
    public int minTransfers(int[][] transactions) {
        minTransac = Integer.MAX_VALUE;
        /** <id, balance> hashmap. */
        Map<Integer, Integer> idToDebts = new HashMap<>();
        for (int[] trans : transactions) {
            idToDebts.put(trans[1], idToDebts.getOrDefault(trans[1], 0) + trans[2]);
            idToDebts.put(trans[0], idToDebts.getOrDefault(trans[0], 0) - trans[2]);
        }

        int[] debts = new int[idToDebts.size()];
        int index = 0;
        for (int i : idToDebts.keySet()) {
            debts[index] = idToDebts.get(i);
            index++;
        }
        backtracking(0, debts, 0);
        return minTransac;

    }

    private void backtracking(int index, int[] debts, int numTrans) {
        /** we need to handle the balance of non zeros. */
        while (index < debts.length && debts[index] == 0) {
            index++;
        }
        if (index == debts.length) {
            minTransac = Math.min(minTransac, numTrans);
            return;
        }

        /** try to dfs and backtrack in the remaining debts 
        by "paying" debts[index] to another debt[i] of a different type. */
        for (int i = index + 1; i < debts.length; i++) {
            if (debts[index] * debts[i] < 0) {
                debts[i] = debts[i] + debts[index];
                /** we handled the balance of debts[index], go to the next one. */
                backtracking(index + 1, debts, numTrans + 1);
                debts[i] = debts[i] - debts[index];
            }
        }
    }

    /** [[0,1,1],[1,2,1],[2,3,4],[3,4,5]] -> 3*/
}
