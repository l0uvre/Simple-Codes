/** LC 2128 -- Bit Manipulation */


public class RemoveOnesRowColumnFlips {

    /** facts:
    1. Does the order, in which you do the operations, matter?

    2. No, it does not. An element will keep its original value 
    if the number of operations done on it is even and vice versa. 
    This also means that doing more than 1 operation on the same row or column is unproductive.  

    3. Try working backward, start with a matrix of all zeros and 
    try to construct grid using operations.

    4. Start with operations on columns, after doing them what do you notice about each row?

    5. Each row is the exact same. 
    If we then flip some rows, that leaves only two possible arrangements
    for each row: the same as the original or the opposite.
    */
    public boolean removeOnes(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int[] row = new int[n];
        for (int i = 1; i < m; i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                row[j] = grid[0][j] ^ grid[i][j];
                sum += row[j];
            }
            if (sum != n && sum != 0) {
                return false;
            }
        }
        return true;
    }

    /** thinking backwards, starting from the all zeros matrix. Firstly, if we filp all columns,
    and then filp some rows, all the rows should be the same as the first or exactly the opposite. */


}
